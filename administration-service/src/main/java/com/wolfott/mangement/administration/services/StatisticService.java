package com.wolfott.mangement.administration.services;

import com.wolfott.mangement.administration.responses.StatResponce;

public interface StatisticService {

    StatResponce getStats(String action);
}
