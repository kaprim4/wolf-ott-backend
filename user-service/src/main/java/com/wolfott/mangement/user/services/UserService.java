package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.models.User;
import com.wolfott.mangement.user.requests.UserCreateRequest;
import com.wolfott.mangement.user.requests.UserUpdateRequest;
import com.wolfott.mangement.user.responses.UserCompactResponse;
import com.wolfott.mangement.user.responses.UserCreateResponse;
import com.wolfott.mangement.user.responses.UserDetailResponse;
import com.wolfott.mangement.user.responses.UserUpdateResponse;
import io.micrometer.core.instrument.config.validate.Validated;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    UserDetailResponse getOne(Long id);

    Page<UserCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);

    Page<UserCompactResponse> getAll(String search, Pageable pageable);

    List<User> getAllUsers();

    UserCreateResponse create(UserCreateRequest request);

    UserUpdateResponse update(Long id, UserUpdateRequest request);

    void delete(Long id);

    String findById(Long memberId);
}
