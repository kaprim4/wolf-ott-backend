package com.wolfott.mangement.user.repositories;

import com.wolfott.mangement.user.models.UserPackage;
import com.wolfott.mangement.user.projections.UserPackageInfo;
import com.wolfott.mangement.user.responses.PackageCompactResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PackageRepository extends JpaRepository<UserPackage, String>, JpaSpecificationExecutor<UserPackage> {
//    List<UserPackage> findByIdIn(Collection<String> ids);

    @Async
    CompletableFuture<List<UserPackageInfo>> findByIdIn(Collection<String> ids);

    @Query("select p from UserPackage p where p.groups like %:groupId%")
    List<UserPackage> findByGroup(@Param("groupId") Long groupId);

}
