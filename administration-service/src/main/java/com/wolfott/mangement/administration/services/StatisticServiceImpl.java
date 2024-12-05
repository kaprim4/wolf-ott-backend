package com.wolfott.mangement.administration.services;

import com.wolfott.mangement.administration.repositories.StatisticRepository;
import com.wolfott.mangement.administration.responses.StatResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private StatisticRepository statisticRepository;

    @Override
    public StatResponce getStats(String action) {
        StatResponce statResponce = new StatResponce();
        if (action.equals("dashboard")) {
            int onlineUsers = statisticRepository.getOnlineUsers();
            statResponce.setOnlineUsers(onlineUsers);
        }
        return statResponce;
    }
}
