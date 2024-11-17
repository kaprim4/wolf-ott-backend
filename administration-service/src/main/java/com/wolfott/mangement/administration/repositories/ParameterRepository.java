package com.wolfott.mangement.administration.repositories;

import com.wolfott.mangement.administration.models.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    Optional<Parameter> findFirstByKey(String key);
}
