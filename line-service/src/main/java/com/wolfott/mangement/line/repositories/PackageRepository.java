package com.wolfott.mangement.line.repositories;

import com.wolfott.mangement.line.models.UserPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<UserPackage, String> {
}
