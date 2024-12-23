package com.wolfott.mangement.user.repositories;

import com.wolfott.mangement.user.models.UserCreditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCreditLogRepository extends JpaRepository<UserCreditLog, Long> {
}
