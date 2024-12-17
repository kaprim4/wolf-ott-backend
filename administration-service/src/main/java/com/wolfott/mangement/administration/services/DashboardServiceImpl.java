package com.wolfott.mangement.administration.services;

import com.wolfott.mangement.administration.responses.GlobalStateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public GlobalStateResponse getGlobalStats(Long userId) {

        GlobalStateResponse stats = new GlobalStateResponse();

        CompletableFuture<Long> onlineUsersFuture = CompletableFuture.supplyAsync(() -> getOnlineUsers(userId));
        CompletableFuture<Long> onlineLinesFuture = CompletableFuture.supplyAsync(() -> getOnlineLines(userId));
        CompletableFuture<Long> activeLinesFuture = CompletableFuture.supplyAsync(() -> getActiveLines(userId));
        CompletableFuture<Long> assignedCreditsFuture = CompletableFuture.supplyAsync(() -> getAssignedCredits(userId));

        CompletableFuture.allOf(onlineUsersFuture, onlineLinesFuture, activeLinesFuture, assignedCreditsFuture).join();

        stats.setOnlineUsers(onlineUsersFuture.join());
        stats.setOnlineLines(onlineLinesFuture.join());
        stats.setActiveLines(activeLinesFuture.join());
        stats.setAssignedCredits(assignedCreditsFuture.join());

        return stats;
    }


    private Long getOnlineUsers(Long userId) {
        String sql = "SELECT COUNT(user_id) as online_users FROM xui.login_logs WHERE status = 'SUCCESS' and type = 'RESELLER' AND user_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, Long.class);
    }

    private Long getOnlineLines(Long userId) {
        String sql = "SELECT COUNT(*) as online_lines FROM xui.lines_live WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, Long.class);
    }

    private Long getActiveLines(Long userId) {
        String sql = """
WITH RECURSIVE user_hierarchy AS (
        SELECT id, owner_id, 1 AS depth
        FROM `users`
        WHERE `id` = ?
        UNION ALL
        SELECT u.id, u.owner_id, uh.depth + 1
        FROM `users` u
        INNER JOIN user_hierarchy uh ON u.`owner_id` = uh.`id`
        WHERE uh.depth < 10
    )
    SELECT COUNT(*)
    FROM `lines` l
    WHERE l.`member_id` IN (
        SELECT uh.id FROM user_hierarchy uh
    )
    """;
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, Long.class);
    }

    private Long getAssignedCredits(Long userId) {
        String sql = "SELECT SUM(amount) as assigned_credits FROM xui.users_credits_logs WHERE admin_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, Long.class);
    }
}
