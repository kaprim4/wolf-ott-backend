package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.responses.UserLogCompactResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface UserLogService {

    Page<UserLogCompactResponse> getAll(Pageable pageable);
}
