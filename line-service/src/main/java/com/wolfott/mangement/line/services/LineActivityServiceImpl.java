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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class LineActivityServiceImpl implements LineActivityService {
    @Autowired
    private LineActivityRepository activityRepository;
    @Autowired
    private LineRepository lineRepository;
    @Autowired
    private StreamRepository streamRepository;
    @Override
    public Page<LineActivityCompactResponse> getAll(Pageable pageable) {
        Page<LineActivity> activities = activityRepository.findAll(pageable);
        List<Long> lines = activities.map(LineActivity::getUserId).toList();
        List<Long> streams = activities.map(LineActivity::getStreamId).toList();

        CompletableFuture<List<Line>> linesList = lineRepository.findByIdIn(lines);
        CompletableFuture<List<Stream>> streamsList = streamRepository.findByIdIn(streams);

        CompletableFuture.allOf(linesList, streamsList).join();
        return activities.map(a -> {
            return toResponse(a, linesList.join(), streamsList.join());
        });
    }

    private LineActivityCompactResponse toResponse(LineActivity activity, List<Line> lines, List<Stream> streams){
        LineActivityCompactResponse response = new LineActivityCompactResponse();
        response.setId(activity.getActivityId());
        Line line = lines.stream().filter(l -> Objects.equals(activity.getUserId(), l.getId())).findFirst().orElse(null);
        if (line != null){
            response.setLine_id(line.getId());
            response.setLine_name(line.getUsername());
        }
        Stream stream = streams.stream().filter(s -> Objects.equals(activity.getStreamId(), s.getId())).findFirst().orElse(null);
        if (stream != null){
            response.setStream_id(activity.getStreamId());
            response.setStream_name(stream.getStreamDisplayName());
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
