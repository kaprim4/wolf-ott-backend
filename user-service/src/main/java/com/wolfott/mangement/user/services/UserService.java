package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.responses.UserCompactResponse;
import com.wolfott.mangement.user.responses.UserDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface UserService {
    UserDetailResponse getOne(Long id);
    Page<UserCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);
}
