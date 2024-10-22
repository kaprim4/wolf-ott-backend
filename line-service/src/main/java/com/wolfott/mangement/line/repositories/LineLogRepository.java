package com.wolfott.mangement.line.repositories;

import com.wolfott.mangement.line.models.LineLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineLogRepository extends JpaRepository<LineLog, Long> {
}
