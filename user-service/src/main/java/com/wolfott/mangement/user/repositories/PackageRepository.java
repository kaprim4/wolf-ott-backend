package com.wolfott.mangement.user.repositories;

import com.wolfott.mangement.user.models.UserPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PackageRepository extends JpaRepository<UserPackage, String>, JpaSpecificationExecutor<UserPackage> {
}
