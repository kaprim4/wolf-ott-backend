package com.wolfott.auth.repositories;

import com.wolfott.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = """
    WITH RECURSIVE user_hierarchy AS (
        SELECT id, owner_id, 1 AS depth
        FROM `users`
        WHERE `id` = :id
        UNION ALL
        SELECT u.id, u.owner_id, uh.depth + 1
        FROM `users` u
        INNER JOIN user_hierarchy uh ON u.`owner_id` = uh.`id`
        WHERE uh.depth < 10
    )
    SELECT COUNT(l.id)
    FROM `lines` l
    WHERE l.`member_id` IN (
        SELECT uh.id FROM user_hierarchy uh
    )
    AND l.`is_trial` = 0
    ORDER BY id DESC
    """, nativeQuery = true)
    int getCountLineByMemberId(@Param("id") Long id);
}
