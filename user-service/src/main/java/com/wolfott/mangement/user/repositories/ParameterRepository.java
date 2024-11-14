package com.wolfott.mangement.user.repositories;

import com.wolfott.mangement.user.models.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {
}
