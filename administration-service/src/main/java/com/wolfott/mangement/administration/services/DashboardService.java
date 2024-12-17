package com.wolfott.mangement.administration.services;

import com.wolfott.mangement.administration.responses.GlobalStateResponse;

import java.util.Map;

public interface DashboardService {
    GlobalStateResponse getGlobalStats(Long userId);
}
