package com.wolfott.mangement.line.repositories;

import com.wolfott.mangement.line.models.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LineRepository extends JpaRepository<Line, Long>, JpaSpecificationExecutor<Line> {
}
