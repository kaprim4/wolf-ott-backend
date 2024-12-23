package com.wolfott.auth.repositories;

import com.wolfott.auth.models.User;
import com.wolfott.auth.models.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    @Query("SELECT g FROM UserGroup g LEFT JOIN User u ON (u.group.groupId = g.groupId) WHERE u.id = :userId")
    Optional<UserGroup> findByUserId(Long userId);
}
