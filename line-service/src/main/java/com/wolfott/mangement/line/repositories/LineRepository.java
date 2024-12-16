package com.wolfott.mangement.line.repositories;

import com.wolfott.mangement.line.models.Line;
import com.wolfott.mangement.line.models.LineActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface LineRepository extends JpaRepository<Line, Long>, JpaSpecificationExecutor<Line> {

    //    @Query("SELECT l.id, l.username, l.password, u.username AS owner, l.enabled AS status, l.lastActivity AS online, l.isTrial AS trial, l.adminEnabled AS active, COUNT(la.activityId) AS connections, l.expDate AS expiration, l.updated AS lastConnection FROM Line l LEFT JOIN LineActivity la ON (la.activityId = l.lastActivity) LEFT JOIN User u ON (u.id = l.memberId) GROUP BY l.id, l.username, l.password, l.memberId, l.enabled, l.lastActivity, l.isTrial, l.adminEnabled, l.expDate, l.updated")
    //    List<LineList> findLinesList();
    @Async
    CompletableFuture<List<Line>> findByIdIn(Collection<Long> ids);

    @Query("SELECT l FROM Line l WHERE l.enabled = 1 ORDER BY l.createdAt DESC")
    List<Line> findTop10ByEnabledOrderByCreatedAtDesc();

    @Query("SELECT COUNT(l) FROM Line l WHERE l.createdAt > :lastWeekStartTimestamp")
    int countByCreatedAtAfter(@Param("lastWeekStartTimestamp") Long lastWeekStartTimestamp);

    @Query("SELECT COUNT(l) FROM Line l WHERE l.createdAt >= :startTimestamp AND l.createdAt < :endTimestamp")
    Long countByCreatedAtBetween(@Param("startTimestamp") Long startTimestamp, @Param("endTimestamp") Long endTimestamp);

    @Query("SELECT COUNT(l) FROM Line l WHERE l.member.id = :id")
    int getCountByMemberId(@Param("id") Long id);

    @Query(value = """
            CREATE TEMPORARY TABLE temp_user_hierarchy AS
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
            SELECT uh.id
            FROM user_hierarchy uh;
            
            SELECT l.*
            FROM `lines` l
            WHERE l.`member_id` IN (
                SELECT id FROM temp_user_hierarchy
            );
            """, nativeQuery = true)
    List<Line> findAllLinesRecursively(@Param("userId") Long userId);

    Optional<Line> findByLastActivity(Long lastActivity);

}
