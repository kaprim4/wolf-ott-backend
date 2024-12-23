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
        SELECT *, 1 AS depth FROM `users` WHERE `owner_id` = :ownerId
        UNION ALL
        SELECT u.*, uh.`depth` + 1 FROM `users` u
        INNER JOIN `user_hierarchy` uh ON u.`owner_id` = uh.`id`
        WHERE uh.`depth` < 10
    )
    SELECT DISTINCT (`id`) FROM `user_hierarchy`;
""", nativeQuery = true)
    List<Long> findAllHierarchyByOwnerId(@Param("ownerId") Long ownerId);

    @Query(value = """
    SELECT COUNT(l.id) AS lineCount\s
    FROM `users` u\s
    LEFT JOIN `lines` l ON u.id = l.member_id\s
    WHERE u.id = :memberId
""", nativeQuery = true)
    Long findLineCountsByMemberId(@Param("memberId") Long memberId);

    @Query("SELECT u FROM User u WHERE u.id IN (:ids) ORDER BY u.id DESC")
    Page<User> findAllByIds(List<Long> ids, Pageable pageable);
}
