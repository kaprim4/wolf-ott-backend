package com.wolfott.mangement.administration.repositories;

import com.wolfott.mangement.administration.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
