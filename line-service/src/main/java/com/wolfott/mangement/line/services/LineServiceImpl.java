package com.wolfott.mangement.line.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolfott.mangement.line.configs.UserServiceClient;
import com.wolfott.mangement.line.exceptions.*;
import com.wolfott.mangement.line.mappers.BouquetMapper;
import com.wolfott.mangement.line.mappers.LineMapper;
import com.wolfott.mangement.line.models.*;
import com.wolfott.mangement.line.repositories.*;
import com.wolfott.mangement.line.requests.LineCreateRequest;
import com.wolfott.mangement.line.requests.LineUpdateRequest;
import com.wolfott.mangement.line.requests.PatchRequest;
import com.wolfott.mangement.line.responses.*;
import com.wolfott.mangement.line.specifications.LineSpecifications;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class LineServiceImpl implements LineService {
    @Autowired
    LineRepository lineRepository;
    @Autowired
    LineLiveRepository lineLiveRepository;
    @Autowired
    LineActivityRepository lineActivityRepository;
    @Autowired
    ParameterRepository parameterRepository;
    @Autowired
    LineSpecifications lineSpecifications;
    @Autowired
    LineMapper lineMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BouquetRepository bouquetRepository;
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private CreditsLogRepository creditsLogRepository;
    @Autowired
    BouquetMapper bouquetMapper;
    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    public LineDetailResponse getOne(Long id) {
        Line line = lineRepository.findById(id).orElseThrow(() -> new LineNotFoundException("Line not found"));
        return lineMapper.toLineDetailResponse(line);
    }

    @Override
    public List<BouquetCompactResponse> getLineBouquets(Long id) {
        Line line = lineRepository.findById(id).orElseThrow(LineNotFoundException::new);
        log.info("bouquet's : {}", line.getBouquet());

        String bouquetJson = line.getBouquet(); // Assume this is a JSON string
        List<Long> lineBouquets;

        try {
            if (bouquetJson == null)
                lineBouquets = new ArrayList<>();
            else
                lineBouquets = new ObjectMapper().readValue(bouquetJson, new TypeReference<List<Long>>() {
                });
        } catch (IOException e) {
            log.error("Failed to deserialize bouquet JSON", e);
            throw new RuntimeException("Deserialization error", e);
        }

        List<Bouquet> bouquets = bouquetRepository.findAllById(lineBouquets);
        return bouquetMapper.toCompactResponse(bouquets);
    }


    @Override
    public int getLinesCount() {
        return 0;
    }

    @Override
    public List<LineCompactResponse> getAll(Map<String, Object> filters) {
        Long ownerId = getCurrentUserId();
        if (ownerId == null) {
            throw new UnauthorizedAccessException("User not authenticated");
        }
        Specification<Line> spec = lineSpecifications.dynamic(filters);
        if (!isAdmin()) {
            spec = spec.and(LineSpecifications.hasMemberId(ownerId));
        }
        List<Line> list = lineRepository.findAll(spec, Sort.by(Sort.Direction.DESC, "id"));
        return lineMapper.toLineCompactResponsePage(list);
    }

    @Override
    public Page<LineCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Long ownerId = getCurrentUserId();
        if (ownerId == null) {
            throw new UnauthorizedAccessException("User not authenticated");
        }
        Page<Line> page = findAllLinesRecursivelyPaginated(ownerId, pageable);
        page.stream().parallel().forEach(line -> {
            if (line.getMemberId() != null) {
                User member = userRepository.findById(line.getMemberId()).orElse(new User());
                String username = member.getUsername();
                member.setUsername(username);
                line.setMember(member);
            }
        });
        return lineMapper.toLineCompactResponsePage(page);
    }

    @Override
    public List<LineCompactResponse> getExpiredLine(Long limit) {
        Long ownerId = getCurrentUserId();
        if (ownerId == null) {
            throw new UnauthorizedAccessException("User not authenticated");
        }
        List<Line> lineList = lineRepository.findAllExpiredLinesRecursively(ownerId, limit);
        return lineMapper.toLineCompactResponsePage(lineList);
    }

    public Page<Line> findAllLinesRecursivelyPaginated(Long ownerId, Pageable pageable) {
        List<Line> lines = lineRepository.findAllLinesRecursively(ownerId);
        int total = lines.size();
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), total);
        if (start > total) {
            return new PageImpl<>(Collections.emptyList(), pageable, total);
        }
        List<Line> paginatedLines = lines.subList(start, end);
        return new PageImpl<>(paginatedLines, pageable, total);
    }

    @Override
    public Page<LineList> getAllforListing(Map<String, Object> filters, Pageable pageable) {
        return null;
    }

    @Override
    @Transactional
    public LineCreateResponse create(LineCreateRequest request) {
        Line line = lineMapper.toLine(request);

        // Perform the credit deduction asynchronously
        CompletableFuture<Void> creditCheckFuture = handleCreditDeductionAsync(line);

        // Proceed with saving the line as usual
        Long createdAt = System.currentTimeMillis() / 1000;
        line.setCreatedAt(createdAt);
        line = lineRepository.save(line);

        if (line.getUseVPN()) {
            line = this.changeVPN(line);
        }

        // Wait for the credit deduction to finish
        creditCheckFuture.join(); // This will block until the credit check and deduction are done

        return lineMapper.toLineCreateResponse(line);
    }

    private CompletableFuture<Void> handleCreditDeductionAsync(Line line) {
        return CompletableFuture.runAsync(() -> {
            Long packageId = line.getPackageId();
            Optional<UserPackage> optPackage = packageRepository.findById(packageId.toString());

            if (optPackage.isPresent()) {
                Long ownerId = line.getMemberId() > 0 ? line.getMemberId() : getCurrentUserId();
                User owner = userRepository.findById(ownerId).orElseThrow(OwnerNotFoundException::new);
                UserPackage pkg = optPackage.get();

                Float cost = 0f;
                if (pkg.getIsOfficial()) {
                    // Official credits deduction logic
                    cost = pkg.getOfficialCredits();
                    if (cost > owner.getCredits()) {
                        throw new InsufficientCreditsException("Not enough credits to create line. Required: " + cost + ", Available: " + owner.getCredits());
                    }
                    Float remain = owner.getCredits() - cost;
                    owner.setCredits(remain);
                    owner = userRepository.save(owner);
                } else {
                    // Trial credits deduction logic
                    cost = pkg.getTrialCredits();
                    if (cost > owner.getCredits()) {
                        throw new InsufficientCreditsException("Not enough credits to create line. Required: " + cost + ", Available: " + owner.getCredits());
                    }
                    Float remain = owner.getCredits() - cost;
                    owner.setCredits(remain);
                    owner = userRepository.save(owner);
                }

                UserCreditLog creditLog = UserCreditLog.builder()
                        .amount(cost)
                        .adminId(getCurrentUserId())
                        .targetId(owner.getId())
                        .timestamp(System.currentTimeMillis()/1000)
//                        .reason("Credit deduction for package")  // Optionally set a reason for the credit deduction, if needed
                        .build();
                creditsLogRepository.save(creditLog);
            }
        });
    }

    @Override
    public LineUpdateResponse update(Long id, LineUpdateRequest request) {
        Line line = lineRepository.findById(id).orElseThrow(() -> new LineNotFoundException("Line not found"));
        line = lineMapper.mergeLine(request, line);
        line = lineRepository.save(line);
        return lineMapper.toLineUpdateResponse(line);
    }

    @Override
    public LinePatchResponse update(Long id, PatchRequest request) {
        Line line = lineRepository.findById(id).orElseThrow(LineNotFoundException::new);
        applyPatch(line, request);
        lineRepository.save(line);
        return lineMapper.toLinePatchResponse(line);
    }


    private void applyPatch(Line line, PatchRequest patch) {
        try {
            String path = patch.getPath();
            String op = patch.getOp();
            Object value = patch.getValue();

            // Handle different operations
            switch (op.toLowerCase()) {
                case "replace":
                    applyReplaceOperation(line, path, value);
                    break;
                case "add":
                    applyAddOperation(line, path, value);
                    break;
                case "remove":
                    applyRemoveOperation(line, path);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported operation: " + op);
            }
        } catch (Exception e) {
            throw new PatchOperationException("Failed to apply patch operation", e);
        }
    }

    private void applyReplaceOperation(Line line, String path, Object value) {
        // Use BeanWrapper to set properties dynamically by path
        BeanWrapper wrapper = new BeanWrapperImpl(line);

        // Here we're assuming 'path' is a valid field name (e.g., "/name")
        String fieldName = path.substring(1); // Remove leading "/"

        // Directly set the field using the BeanWrapper
        wrapper.setPropertyValue(fieldName, value);
    }

    private void applyAddOperation(Line line, String path, Object value) {
        // If it's an optional field, we might want to set a new value
        BeanWrapper wrapper = new BeanWrapperImpl(line);
        String fieldName = path.substring(1); // Remove leading "/"
        wrapper.setPropertyValue(fieldName, value);
    }

    private void applyRemoveOperation(Line line, String path) {
        // Remove operation, set the field value to null (or default value)
        BeanWrapper wrapper = new BeanWrapperImpl(line);
        String fieldName = path.substring(1); // Remove leading "/"
        wrapper.setPropertyValue(fieldName, null);
    }

    @Override
    public void delete(Long id) {
        lineRepository.deleteById(id);
    }

    @Override
    public void changeVPN(Long id) {
        Line line = lineRepository.findById(id).orElseThrow(LineNotFoundException::new);
        Parameter parm = parameterRepository.findFirstByKey("vpn").orElseThrow(() -> new ParameterNotFoundException("No VPN Parameter Found"));
        String value = parm.getValue();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
//            Map<String, Object> config = objectMapper.readValue(value, new TypeReference<Map<String, Object>>() {});
            List<Map<String, Object>> config = objectMapper.readValue(value, new TypeReference<List<Map<String, Object>>>() {});
            List<String> vpnDnsList = new ArrayList<>();
            if(!config.isEmpty())
                vpnDnsList = (List<String>) config.get(0).get("dns");

            if (vpnDnsList.isEmpty())
                return;

            Random random = new Random();
            String randomVpnDns = vpnDnsList.get(random.nextInt(vpnDnsList.size()));
            line.setVpnDns(randomVpnDns);

            lineRepository.save(line);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid format for VPN DNS list in parameter value", e);
        }
    }

    @Override
    public Line changeVPN(Line line) {
        Parameter parm = parameterRepository.findFirstByKey("vpn").orElseThrow(() -> new ParameterNotFoundException("No VPN Parameter Found"));
        String value = parm.getValue();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
//            Map<String, Object> config = objectMapper.readValue(value, new TypeReference<Map<String, Object>>() {});
            List<Map<String, Object>> config = objectMapper.readValue(value, new TypeReference<List<Map<String, Object>>>() {});
            List<String> vpnDnsList = new ArrayList<>();
            if(!config.isEmpty())
                vpnDnsList = (List<String>) config.get(0).get("dns");

            if (vpnDnsList.isEmpty())
                return line;

            Random random = new Random();
            String randomVpnDns = vpnDnsList.get(random.nextInt(vpnDnsList.size()));
            line.setVpnDns(randomVpnDns);

            return lineRepository.save(line);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid format for VPN DNS list in parameter value", e);
        }
    }

    @Override
    public LineUpdateResponse suspendLine(Long id) {
        Line line = lineRepository.findById(id).orElseThrow(LineNotFoundException::new);
        line.setAdminEnabled(line.getAdminEnabled() == 0 ? 1 : 0);
        lineRepository.save(line);
        return lineMapper.toLineUpdateResponse(line);
    }

    @Override
    public LineUpdateResponse disableLine(Long id) {
        Line line = lineRepository.findById(id).orElseThrow(LineNotFoundException::new);
        line.setEnabled(line.getEnabled() == 0 ? 1 : 0);
        lineRepository.save(line);
        return lineMapper.toLineUpdateResponse(line);
    }

    @Override
    public void killLineLives(Long id) {
        Line line = lineRepository.findById(id).orElseThrow(LineNotFoundException::new);
        lineLiveRepository.deleteByActivityId(line.getLastActivity());
    }

    @Override
    public void killLineConnections(Long id) {
        Line line = lineRepository.findById(id).orElseThrow(LineNotFoundException::new);
        lineActivityRepository.deleteByActivityId(line.getLastActivity());
    }

    @Override
    public List<Line> getLinesByPresetId(Long id) {
        return lineRepository.getLinesByPresetId(id);
    }

    @Override
    public void saveAll(List<Line> lineList) {
        lineRepository.saveAll(lineList);
    }

    public List<LineCompactResponse> getLastRegisteredLines() {
        return lineRepository.findTop10ByEnabledOrderByCreatedAtDesc()
                .stream()
                .map(line -> {
                    return lineMapper.toLineCompactResponse(line);
                })
                .toList();
    }

    public int getLastWeekCount() {
        long lastWeekStartTimestamp = LocalDate.now().minusDays(7).atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
        return lineRepository.countByCreatedAtAfter(lastWeekStartTimestamp);
    }

    public int getCountByMemberId(Long id) {
        return lineRepository.getCountByMemberId(id);
    }

    public Map<String, Long> getCreatedLinesLastSixMonths() {
        LocalDate now = LocalDate.now();
        Map<String, Long> createdCounts = new HashMap<>();

        for (int i = 0; i < 6; i++) {
            LocalDate date = now.minusMonths(i);
            Long count = lineRepository.countByCreatedAtBetween(
                    date.withDayOfMonth(1).atStartOfDay(ZoneId.systemDefault()).toEpochSecond(),
                    date.plusMonths(1).withDayOfMonth(1).atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
            );
            createdCounts.put(date.getMonth().name(), count);
        }
        return createdCounts;
    }


    // Helper method to get current authenticated user's role
    private Authentication getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    // Helper method to get user ID (or any other user details you need)
    private Long getCurrentUserId() {
        Authentication auth = getPrincipal();
        if (auth != null) {
            Object principal = auth.getPrincipal();
            System.out.println("Principal: " + principal);
            if (principal instanceof User) {
                return ((User) principal).getId();
            }
        }
        return null;
    }


    // Helper method to check if the current user is an admin
    private boolean isAdmin() {
        Authentication auth = getPrincipal();
        Object principal = auth.getPrincipal();
//        return auth != null && auth.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        if (principal instanceof User) {
            return ((User) principal).isAdmin();
        }
        return false;
    }

}