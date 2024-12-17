package com.wolfott.mangement.user.repositories;

import com.wolfott.mangement.user.models.UserLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogRepository extends JpaRepository<UserLog, Long> {

    Page<UserLog> findAllByOwner(Long owner, Pageable pageable);
}
