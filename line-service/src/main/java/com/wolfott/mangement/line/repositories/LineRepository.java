package com.wolfott.mangement.line.repositories;

import com.wolfott.mangement.line.models.Line;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LineRepository extends JpaRepository<Line, Long> {
}
