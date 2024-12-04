package com.wolfott.mangement.administration.controllers;

import com.wolfott.mangement.administration.responses.GlobalStateResponse;
import com.wolfott.mangement.administration.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/global-state")
    public GlobalStateResponse getGlobalState(){
        return dashboardService.getGlobalStats();
    }
}
