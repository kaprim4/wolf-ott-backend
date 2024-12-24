package com.wolfott.mangement.line.repositories;

import com.wolfott.mangement.line.models.UserCreditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditsLogRepository extends JpaRepository<UserCreditLog, Long> {
}
