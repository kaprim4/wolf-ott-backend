package com.wolfott.mangement.user.repositories;

import com.wolfott.mangement.user.models.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogRepository extends JpaRepository<UserLog, Long> {
}
