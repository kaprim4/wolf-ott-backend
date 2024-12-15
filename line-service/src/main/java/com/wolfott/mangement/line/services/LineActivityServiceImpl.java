package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.models.Line;
import com.wolfott.mangement.line.models.LineActivity;
import com.wolfott.mangement.line.models.LineLog;
import com.wolfott.mangement.line.models.Stream;
import com.wolfott.mangement.line.repositories.LineActivityRepository;
import com.wolfott.mangement.line.repositories.LineRepository;
import com.wolfott.mangement.line.repositories.StreamRepository;
import com.wolfott.mangement.line.responses.LineActivityCompactResponse;
import com.wolfott.mangement.line.responses.LineLogCompactResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LineActivityServiceImpl implements LineActivityService {
    @Autowired
    private LineActivityRepository activityRepository;
    @Autowired
    private LineRepository lineRepository;
    @Autowired
    private StreamRepository streamRepository;


    @Override
    public Page<LineActivityCompactResponse> getAllByUserId(Long userId, Pageable pageable) {
        List<Line> lines = lineRepository.findAllLinesRecursively(userId);
        log.info("{} lines found", lines.size());
        List<Long> linesActivitiesIds = lines.stream()
                .map(Line::getLastActivity)
                .filter(Objects::nonNull)
                .toList();
        Page<LineActivity> activities = activityRepository.findByActivityIdIn(linesActivitiesIds, pageable);
        return activities.map(this::toResponse);
    }

    private LineActivityCompactResponse toResponse(LineActivity activity) {
        LineActivityCompactResponse response = new LineActivityCompactResponse();
        response.setId(activity.getActivityId());
        Optional<Line> lineOptional = lineRepository.findByLastActivity(activity.getActivityId());
        if(lineOptional.isPresent()) {
            Line line = lineOptional.get();
            response.setLine_id(line.getId());
            response.setLine_name(line.getUsername());
        }
        if (activity.getStreamId() != null) {
            streamRepository.findById(activity.getStreamId()).ifPresent(stream -> {
                response.setStream_id(stream.getId());
                response.setStream_name(stream.getStreamDisplayName());
            });
        }
        response.setIp(activity.getUserIp());
        response.setCountry(activity.getGeoipCountryCode());
        response.setIsp(activity.getIsp());
        response.setStartAt(new Date(activity.getDateStart()));
        response.setEndAt(new Date(activity.getDateEnd()));
        response.setPlayer(activity.getUserAgent());
        response.setOutput(activity.getContainer());

        return response;
    }
}
