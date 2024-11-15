package com.wolfott.mangement.user.repositories;

import brave.internal.collect.UnsafeArrayMap;
import com.wolfott.mangement.user.models.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    Optional<Parameter> findFirstByKey(String key);
}
