package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.responses.UserLogCompactResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserLogService {
    Page<UserLogCompactResponse> getAll(Pageable pageable);
}
