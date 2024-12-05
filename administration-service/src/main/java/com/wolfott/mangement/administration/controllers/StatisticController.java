package com.wolfott.mangement.administration.controllers;

import com.wolfott.mangement.administration.models.Application;
import com.wolfott.mangement.administration.responses.StatResponce;
import com.wolfott.mangement.administration.services.ApplicationService;
import com.wolfott.mangement.administration.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/statistic")
public class StatisticController
{
    @Autowired
    private StatisticService statisticService;

    @GetMapping("/{action}")
    public StatResponce getStats(@PathVariable("action") String action) {
        return statisticService.getStats(action);
    }
}
