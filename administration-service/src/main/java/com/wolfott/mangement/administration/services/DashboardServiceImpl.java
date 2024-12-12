package com.wolfott.mangement.administration.services;

import com.wolfott.mangement.administration.responses.GlobalStateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public GlobalStateResponse getGlobalStats() {
        GlobalStateResponse stats = new GlobalStateResponse();

        CompletableFuture<Long> onlineUsersFuture = CompletableFuture.supplyAsync(this::getOnlineUsers);
        CompletableFuture<Long> onlineLinesFuture = CompletableFuture.supplyAsync(this::getOnlineLines);
        CompletableFuture<Long> activeLinesFuture = CompletableFuture.supplyAsync(this::getActiveLines);
        CompletableFuture<Long> assignedCreditsFuture = CompletableFuture.supplyAsync(this::getAssignedCredits);

        CompletableFuture.allOf(onlineUsersFuture, onlineLinesFuture, activeLinesFuture, assignedCreditsFuture).join();

        stats.setOnlineUsers(onlineUsersFuture.join());
        stats.setOnlineLines(onlineLinesFuture.join());
        stats.setActiveLines(activeLinesFuture.join());
        stats.setAssignedCredits(assignedCreditsFuture.join());

        return stats;
    }


    private Long getOnlineUsers() {
        String sql = "SELECT COUNT(user_id) as online_users FROM xui.login_logs WHERE status = 'SUCCESS' and type = 'RESELLER'";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    private Long getOnlineLines() {
        String sql = "SELECT COUNT(*) as online_lines FROM xui.lines_live";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    private Long getActiveLines() {
        String sql = "SELECT COUNT(*) as active_lines FROM xui.lines WHERE last_activity IS NOT NULL";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    private Long getAssignedCredits() {
        String sql = "SELECT SUM(amount) as assigned_credits FROM xui.users_credits_logs";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }
}
