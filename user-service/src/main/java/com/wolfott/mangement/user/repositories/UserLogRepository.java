package com.wolfott.mangement.user.repositories;

import com.wolfott.mangement.user.models.UserLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserLogRepository extends JpaRepository<UserLog, Long> {

    @Query("SELECT l FROM UserLog l WHERE l.owner=:owner ORDER BY l.id DESC")
    Page<UserLog> findAllByOwner(Long owner, Pageable pageable);
}
