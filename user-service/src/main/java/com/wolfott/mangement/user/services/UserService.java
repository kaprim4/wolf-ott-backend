package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.requests.UserCreateRequest;
import com.wolfott.mangement.user.requests.UserUpdateRequest;
import com.wolfott.mangement.user.responses.UserCompactResponse;
import com.wolfott.mangement.user.responses.UserCreateResponse;
import com.wolfott.mangement.user.responses.UserDetailResponse;
import com.wolfott.mangement.user.responses.UserUpdateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface UserService {
    UserDetailResponse getOne(Long id);
    Page<UserCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);
    Page<UserCompactResponse> getAll(String search, Pageable pageable);
    UserCreateResponse create(UserCreateRequest request);
    UserUpdateResponse update(Long id, UserUpdateRequest request);
    void delete(Long id);
}
