package com.wolfott.stream_mangement.services;


import com.wolfott.stream_mangement.responses.EpisodeCompactResponse;
import com.wolfott.stream_mangement.responses.EpisodeDetailResponse;
import com.wolfott.stream_mangement.responses.SerieCompactResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface EpisodeService {
    EpisodeDetailResponse getOne(Long id);
    List<EpisodeCompactResponse> getAll();
    List<EpisodeCompactResponse> getAll(Map<String, Object> filters);
    Page<EpisodeCompactResponse> getAll(Pageable pageable);
    Page<EpisodeCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);
}
