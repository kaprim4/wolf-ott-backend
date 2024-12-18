package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.exceptions.UnauthorizedAccessException;
import com.wolfott.mangement.user.exceptions.UserNotFoundException;
import com.wolfott.mangement.user.mappers.UserMapper;
import com.wolfott.mangement.user.models.User;
import com.wolfott.mangement.user.models.UserGroup;
import com.wolfott.mangement.user.repositories.GroupRepository;
import com.wolfott.mangement.user.repositories.UserRepository;
import com.wolfott.mangement.user.requests.UserCreateRequest;
import com.wolfott.mangement.user.requests.UserUpdateRequest;
import com.wolfott.mangement.user.responses.UserCompactResponse;
import com.wolfott.mangement.user.responses.UserCreateResponse;
import com.wolfott.mangement.user.responses.UserDetailResponse;
import com.wolfott.mangement.user.responses.UserUpdateResponse;
import com.wolfott.mangement.user.specifications.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
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

        List<User> list = userRepository.findAll(spec, Sort.by(Sort.Direction.DESC, "id"));
        list.stream().parallel().forEach(user -> {
            User member = Optional.ofNullable(user.getOwnerId()).map(userRepository::findById).map(opt -> opt.orElse(new User())).orElse(new User());
//          String username = userServiceClient.getUsernameByMemberId(line.getMemberId());
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
        Specification<User> spec = userSpecification.dynamic(filters);
        if (!isAdmin()) {
            spec = spec.and(UserSpecification.hasMemberId(ownerId));
        }
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id")
        );
        Page<User> page = userRepository.findAll(spec, pageable);
        page.stream().parallel().forEach(user -> {
            User member = userRepository.findById(user.getOwnerId()).orElse(new User());
//          String username = userServiceClient.getUsernameByMemberId(line.getMemberId());
            String username = member.getUsername();
            member.setUsername(username);
            user.setOwner(member);
        });
        return userMapper.toCompactResponse(page);
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

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

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
            if (request.getPassword() != null && !request.getPassword().isEmpty())
                user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setEmail(request.getEmail());
            user.setIp(request.getIp());
            user.setCredits(request.getCredits());
            user.setNotes(request.getNotes());
            user.setStatus(request.getStatus());
            user.setResellerDns(request.getResellerDns());
            user.setOwnerId(ownerId);
            user.setOverridePackages(request.getOverridePackages());
            user.setHue(request.getHue());
            user.setTheme(request.getTheme());
            user.setTimezone(request.getTimezone());
            user.setApiKey(request.getApiKey());
            user.setThumbnail(request.getThumbnail());
            user = userRepository.save(user);
        }

        request.setId(id);

        user = userMapper.toUser(request, user);
        if (user.getOwnerId() == null)
            user.setOwnerId(ownerId);
        if (!isAdmin() && user.getOwnerId() != ownerId)
            user.setOwnerId(ownerId);

        user = userRepository.save(user);
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
