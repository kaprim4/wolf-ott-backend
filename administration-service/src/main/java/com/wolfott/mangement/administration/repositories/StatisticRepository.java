package com.wolfott.mangement.administration.repositories;

import com.wolfott.mangement.administration.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StatisticRepository extends JpaRepository<Application, Long> {

    @Query(value = "SELECT COUNT(DISTINCT user_id) as online_users from login_logs where status = 'SUCCESS'", nativeQuery = true)
    int getOnlineUsers();
}
