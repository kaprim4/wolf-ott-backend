package com.wolfott.mangement.line.repositories;

import com.wolfott.mangement.line.models.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    Optional<Parameter> findFirstByKey(String key);
}
