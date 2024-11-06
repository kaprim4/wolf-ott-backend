package com.wolfott.mangement.line.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolfott.mangement.line.configs.UserServiceClient;
import com.wolfott.mangement.line.exceptions.LineNotFoundException;
import com.wolfott.mangement.line.mappers.BouquetMapper;
import com.wolfott.mangement.line.mappers.LineMapper;
import com.wolfott.mangement.line.models.Bouquet;
import com.wolfott.mangement.line.models.Line;
import com.wolfott.mangement.line.models.LineList;
import com.wolfott.mangement.line.models.User;
import com.wolfott.mangement.line.repositories.BouquetRepository;
import com.wolfott.mangement.line.repositories.LineRepository;
import com.wolfott.mangement.line.repositories.UserRepository;
import com.wolfott.mangement.line.requests.LineCreateRequest;
import com.wolfott.mangement.line.requests.LineUpdateRequest;
import com.wolfott.mangement.line.responses.*;
import com.wolfott.mangement.line.specifications.LineSpecifications;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class LineServiceImpl implements LineService {
    @Autowired
    LineRepository lineRepository;
    @Autowired
    LineSpecifications lineSpecifications;
    @Autowired
    LineMapper lineMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BouquetRepository bouquetRepository;
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
        Specification<Line> spec = lineSpecifications.dynamic(filters);
        List<Line> list = lineRepository.findAll(spec);
//        List<Line> lines = list.stream().parallel()
//                .map(line -> {
//                    if (line.getMemberId() != null){
//                        User member = userRepository.findById(line.getMemberId()).orElse(new User());
////                    String username = userServiceClient.getUsernameByMemberId(line.getMemberId());
//                        String username = member.getUsername();
//                        member.setUsername(username);
//                        line.setMember(member);
//                    }
//                    return line;
//                }).toList();
        return lineMapper.toLineCompactResponsePage(list);
    }

    @Override
    public Page<LineCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<Line> spec = lineSpecifications.dynamic(filters);
        Page<Line> page = lineRepository.findAll(spec, pageable);
        List<Line> lines = page.getContent().stream().parallel()
                .map(line -> {
                    if (line.getMemberId() != null) {
                        User member = userRepository.findById(line.getMemberId()).orElse(new User());
//                    String username = userServiceClient.getUsernameByMemberId(line.getMemberId());
                        String username = member.getUsername();
                        member.setUsername(username);
                        line.setMember(member);
                    }
                    return line;
                }).toList();
        page = new PageImpl<>(
                lines,
                pageable,
                page.getTotalElements()
        );
        return lineMapper.toLineCompactResponsePage(page);
    }

    @Override
    public Page<LineList> getAllforListing(Map<String, Object> filters, Pageable pageable) {
        return null;
    }

    @Override
    public LineCreateResponse create(LineCreateRequest request) {
        Line line = lineMapper.toLine(request);
        Long createdAt = System.currentTimeMillis() / 1000;
        line.setCreatedAt(createdAt);
        line = lineRepository.save(line);
        return lineMapper.toLineCreateResponse(line);
    }

    @Override
    public LineUpdateResponse update(Long id, LineUpdateRequest request) {
        Line line = lineRepository.findById(id).orElseThrow(() -> new LineNotFoundException("Line not found"));
        line = lineMapper.mergeLine(request, line);
        line = lineRepository.save(line);
        return lineMapper.toLineUpdateResponse(line);
    }

    @Override
    public void delete(Long id) {
        lineRepository.deleteById(id);
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

}