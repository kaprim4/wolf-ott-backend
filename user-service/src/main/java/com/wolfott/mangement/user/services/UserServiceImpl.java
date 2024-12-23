package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.exceptions.UnauthorizedAccessException;
import com.wolfott.mangement.user.exceptions.UserNotFoundException;
import com.wolfott.mangement.user.mappers.UserMapper;
import com.wolfott.mangement.user.models.User;
import com.wolfott.mangement.user.models.UserCreditLog;
import com.wolfott.mangement.user.models.UserGroup;
import com.wolfott.mangement.user.models.UserPackage;
import com.wolfott.mangement.user.repositories.GroupRepository;
import com.wolfott.mangement.user.repositories.UserCreditLogRepository;
import com.wolfott.mangement.user.repositories.UserRepository;
import com.wolfott.mangement.user.requests.UserCreateRequest;
import com.wolfott.mangement.user.requests.UserCreditAdjustmentRequest;
import com.wolfott.mangement.user.requests.UserUpdateRequest;
import com.wolfott.mangement.user.responses.*;
import com.wolfott.mangement.user.specifications.UserSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserCreditLogRepository userCreditLogRepository;

    @Autowired
    private UserSpecification userSpecification;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetailResponse getOne(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        return userMapper.toDetailResponse(user);
    }

    @Override
    public List<UserCompactResponse> getAll(Map<String, Object> filters) {
        Long ownerId = getCurrentUserId();
        if (ownerId == null) {
            throw new UnauthorizedAccessException("User not authenticated");
        }

        Specification<User> spec = userSpecification.dynamic(filters);
        if (!isAdmin()) {
            spec = spec.and(UserSpecification.hasMemberId(ownerId));
        }

        List<User> list = userRepository.findAll(spec, Sort.by(Sort.Direction.ASC, "id"));

        User currentUser = userRepository.findById(ownerId).orElseThrow(() ->
                new EntityNotFoundException("Current user not found"));

        if (!list.contains(currentUser)) {
            list.add(currentUser);
        }

        list.sort(Comparator.comparing(User::getId));

        list.stream().parallel().forEach(user -> {
            User member = Optional.ofNullable(user.getOwnerId())
                    .map(userRepository::findById)
                    .map(opt -> opt.orElse(new User()))
                    .orElse(new User());

            String username = member.getUsername();
            member.setUsername(username);
            user.setOwner(member);
        });

        return userMapper.toCompactResponse(list);
    }


    @Override
    public Page<UserCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Long ownerId = getCurrentUserId();
        if (ownerId == null) {
            throw new UnauthorizedAccessException("User not authenticated");
        }
        List<Long> allUsersIds = userRepository.findAllHierarchyByOwnerId(ownerId);
        Page<User> allUsers = userRepository.findAllByIds(allUsersIds, pageable);
        allUsers.stream().parallel().forEach(user -> {
            user.setLineCount(userRepository.findLineCountsByMemberId(user.getId()));
            User owner = userRepository.findById(user.getOwnerId()).orElse(null);
            user.setOwner(owner);
        });
        Page<UserCompactResponse> userCompactResponsePage = allUsers.map(userMapper::toCompactResponse);
        log.info("Total users fetched: {}", userCompactResponsePage.getTotalElements());
        return userCompactResponsePage;
    }

    @Override
    public Page<UserCompactResponse> getAll(String search, Pageable pageable) {
        Specification<User> spec = userSpecification.search(search);
        Page<User> page = userRepository.findAll(spec, pageable);
        return userMapper.toCompactResponse(page);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public UserCreateResponse create(UserCreateRequest request) {
        Long ownerId = getCurrentUserId();
        if (ownerId == null) {
            throw new UnauthorizedAccessException("User not authenticated");
        }

        UserGroup userGroup = groupRepository.findById(request.getMemberGroupId()).orElseThrow(null);

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .ip(null)
                .credits(request.getCredits())
                .notes(request.getNotes())
                .status(request.getStatus())
                .group(userGroup)
                .resellerDns(request.getResellerDns())
                .ownerId(request.getOwnerId())
                .overridePackages(request.getOverridePackages())
                .hue(request.getHue())
                .theme(request.getTheme())
                .timezone(request.getTimezone())
                .apiKey(request.getApiKey())
                .lastLogin(null)
                .timestampDateRegistered(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                .build();

        if (user.getOwnerId() == null)
            user.setOwnerId(ownerId);

        user = userRepository.save(user);
        return userMapper.toCreateResponse(user);
    }

    @Override
    public UserUpdateResponse update(Long id, UserUpdateRequest request) {
        Long ownerId = getCurrentUserId();
        if (ownerId == null) {
            throw new UnauthorizedAccessException("User not authenticated");
        }
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        if (user != null) {
            user.setUsername(request.getUsername());
            if (request.getPassword() != null && !request.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(request.getPassword()));
            } else {
                user.setPassword(user.getPassword());
            }
            UserGroup userGroup = groupRepository.findById(request.getMemberGroupId()).orElseThrow(null);
            user.setEmail(request.getEmail());
            user.setIp(request.getIp());
            user.setCredits(request.getCredits());
            user.setNotes(request.getNotes());
            user.setStatus(request.getStatus());
            user.setGroup(userGroup);
            user.setResellerDns(request.getResellerDns());
            user.setOwnerId(ownerId);
            user.setOverridePackages(request.getOverridePackages());
            user.setHue(request.getHue());
            user.setTheme(request.getTheme());
            user.setTimezone(request.getTimezone());
            user.setApiKey(request.getApiKey());
            user.setThumbnail(request.getThumbnail());

            if (user.getOwnerId() == null)
                user.setOwnerId(ownerId);
            if (!isAdmin() && !Objects.equals(user.getOwnerId(), ownerId))
                user.setOwnerId(ownerId);

            userRepository.saveAndFlush(user);
        }
        return userMapper.toUpdateResponse(user);
    }

    @Override
    public void delete(Long id) {
        Long ownerId = getCurrentUserId();
        if (ownerId == null) {
            throw new UnauthorizedAccessException("User not authenticated");
        }
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        if (!isAdmin() && user.getOwnerId() != ownerId)
            throw new UnauthorizedAccessException("Action not authenticated");
        userRepository.delete(user);
    }

    @Override
    public String findById(Long memberId) {
        User user = userRepository.findById(memberId).orElse(null);
        return user != null ? user.getUsername() : null;
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public UserCreditAdjustmentResponse adjustUserCredits(UserCreditAdjustmentRequest request) {
        Long ownerId = getCurrentUserId();
        if (ownerId == null) {
            throw new UnauthorizedAccessException("User not authenticated");
        }
        User user = userRepository.findById(request.userId()).orElse(null);
        if (user == null)
            return null;
        else {
            user.setCredits(Float.valueOf(request.remaining_credits()));
            user = userRepository.save(user);

            UserCreditLog userCreditLog = UserCreditLog.builder()
                    .adminId(ownerId)
                    .targetId(user.getId())
                    .amount(Float.valueOf(request.cost_credits()))
                    .reason(request.reason())
                    .timestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                    .build();
            userCreditLogRepository.save(userCreditLog);

            return UserCreditAdjustmentResponse.builder()
                    .userId(user.getId())
                    .credits(request.cost_credits())
                    .userCreditLog(userCreditLog)
                    .build();
        }
    }

    @Override
    public UserDetailResponse toggleStatus(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        user.setStatus(!user.getStatus());
        user = userRepository.save(user);
        return userMapper.toDetailResponse(user);
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
