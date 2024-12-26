package com.wolfott.mangement.user.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolfott.mangement.user.exceptions.UnauthorizedAccessException;
import com.wolfott.mangement.user.models.Line;
import com.wolfott.mangement.user.models.User;
import com.wolfott.mangement.user.models.UserLog;
import com.wolfott.mangement.user.projections.UserPackageInfo;
import com.wolfott.mangement.user.repositories.LineRepository;
import com.wolfott.mangement.user.repositories.PackageRepository;
import com.wolfott.mangement.user.repositories.UserLogRepository;
import com.wolfott.mangement.user.repositories.UserRepository;
import com.wolfott.mangement.user.responses.UserLogCompactResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class UserLogServiceImpl implements UserLogService {
    @Autowired
    private UserLogRepository userLogRepository;
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LineRepository lineRepository;

    private final ObjectMapper objectMapper = new ObjectMapper(); // Reuse ObjectMapper instance

    @Override
    public Page<UserLogCompactResponse> getAll(Pageable pageable) {
        Long ownerId = getCurrentUserId();
        if (ownerId == null) {
            throw new UnauthorizedAccessException("User not authenticated");
        }
        Page<UserLog> logs = userLogRepository.findAllByOwner(ownerId, pageable);
        List<Long> owners = logs.map(UserLog::getOwner).toList();
        List<String> packageIds = logs.map(UserLog::getPackageId).filter(Objects::nonNull).map(Object::toString).toList();
        List<UserLog> lineLogs = filterLogsByType(logs.getContent(), "line");
        List<UserLog> userLogs = filterLogsByType(logs.getContent(), "user");

        CompletableFuture<List<Long>> linesFuture = CompletableFuture.supplyAsync(() -> extractDistinctIds(lineLogs));
        CompletableFuture<List<Long>> usersFuture = CompletableFuture.supplyAsync(() -> extractDistinctIds(userLogs));

        CompletableFuture<List<User>> ownersFuture = userRepository.findByIdIn(owners);
        CompletableFuture<List<UserPackageInfo>> packagesFuture = packageRepository.findByIdIn(packageIds);

        CompletableFuture<List<Line>> linesFutureData = linesFuture.thenCompose(lines -> lineRepository.findByIdIn(lines));
        CompletableFuture<List<User>> usersFutureData = usersFuture.thenCompose(users -> userRepository.findByIdIn(users));

        CompletableFuture.allOf(ownersFuture, packagesFuture, linesFutureData, usersFutureData).join();
        List<UserLogCompactResponse> response = createResponses(logs, ownersFuture.join(), packagesFuture.join(), linesFutureData.join(), usersFutureData.join());

        return new PageImpl<>(response, pageable, logs.getTotalElements());
    }

    private List<UserLog> filterLogsByType(List<UserLog> logs, String type) {
        return logs.stream()
                .filter(log -> type.equals(log.getType()))
                .collect(Collectors.toList());
    }

    private List<Long> extractDistinctIds(List<UserLog> logs) {
        return logs.stream()
                .map(this::parseDeletedInfo)
                .filter(Objects::nonNull)
                .map(this::extractId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

    private List<UserLogCompactResponse> createResponses(Page<UserLog> logs, List<User> ownersList, List<UserPackageInfo> packagesList, List<Line> linesList, List<User> usersList) {
        return logs.getContent().stream().map(log -> {
            UserLogCompactResponse obj = new UserLogCompactResponse();
            obj.setId(log.getId());
            obj.setAction(log.getAction());
            obj.setCost(log.getCost());
            obj.setCredit(log.getCreditsAfter());
            obj.setType(log.getType());
            obj.setDate(log.getDate());

            Long ownerId = log.getOwner();
            obj.setOwner_id(ownerId);
            obj.setOwner_username(findUsernameById(ownersList, ownerId));

            Long packageId = log.getPackageId();
            obj.setPackage_id(packageId);
            obj.setPackage_name(findPackageNameById(packagesList, packageId));

            if ("line".equals(log.getType())) {
                Long lineId = extractIdFromLog(log);
                obj.setLine_id(lineId);
                obj.setLine_username(findLineUsernameById(linesList, lineId));
            }

            if ("user".equals(log.getType())) {
                Long userId = extractIdFromLog(log);
                obj.setUser_id(userId);
                obj.setUser_username(findUsernameById(usersList, userId));
            }

            return obj;
        }).collect(Collectors.toList());
    }

    private Long extractIdFromLog(UserLog log) {
        return Optional.ofNullable(log)
                .map(this::parseDeletedInfo)
                .map(this::extractId)
                .orElse(null);
    }

    private String findUsernameById(List<? extends User> users, Long id) {
        return users.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .map(User::getUsername)
                .findFirst()
                .orElse(null);
    }

    private String findLineUsernameById(List<? extends Line> users, Long id) {
        return users.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .map(Line::getUsername)
                .findFirst()
                .orElse(null);
    }

    private String findPackageNameById(List<? extends UserPackageInfo> packages, Long id) {
        return packages.stream()
                .filter(pkg -> Objects.equals(Long.parseLong(pkg.getId()), id))
                .map(UserPackageInfo::getPackageName)
                .findFirst()
                .orElse(null);
    }

    private Map<String, Object> parseDeletedInfo(UserLog log) {
        String json = log.getDeletedInfo();
        try {
            return objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    private Long extractId(Map<String, Object> obj) {
        Object id = obj.get("id");
        return id != null ? Long.parseLong(String.valueOf(id)) : null;
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
}
