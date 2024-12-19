package com.wolfott.mangement.user.repositories;

import com.wolfott.mangement.user.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findOneById(Long id);

    List<User> findByOwnerId(Long ownerId);

    @Async
    CompletableFuture<List<User>> findByIdIn(Collection<Long> ids);

    @Query("SELECT u.id FROM User u WHERE u.ownerId = :currentUserId")
    List<Long> findDirectSubsellers(@Param("currentUserId") Long currentUserId);

    @Query(
            value = "WITH RECURSIVE SubsellerHierarchy AS (" +
                    "   SELECT u.id, u.owner_id FROM users u WHERE u.owner_id = :currentUserId " +  // Correct column name 'owner_id'
                    "   UNION ALL " +
                    "   SELECT u.id, u.owner_id FROM users u " +  // Correct column name 'owner_id'
                    "   JOIN SubsellerHierarchy sh ON u.owner_id = sh.id" +  // Correct column name 'owner_id'
                    ") " +
                    "SELECT id FROM SubsellerHierarchy",
            nativeQuery = true
    )
    List<Long> findAllSubsellers(@Param("currentUserId") Long currentUserId);

    @Query(value = """
            WITH RECURSIVE user_hierarchy AS (
                SELECT id, owner_id, 1 AS depth
                FROM `users`
                WHERE `id` = :userId
                UNION ALL
                SELECT u.id, u.owner_id, uh.depth + 1
                FROM `users` u
                INNER JOIN user_hierarchy uh ON u.`owner_id` = uh.`id`
                WHERE uh.depth < 10
            )
            SELECT u.*
            FROM `users` u
            WHERE u.`id` IN (
                SELECT uh.id FROM user_hierarchy uh
            )
            ORDER BY id DESC
            """, nativeQuery = true)
    Page<User> findAllUsersRecursive(@Param("userId") Long userId, Pageable pageable);
}
