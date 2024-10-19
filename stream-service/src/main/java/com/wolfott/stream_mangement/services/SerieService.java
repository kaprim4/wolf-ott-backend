package com.wolfott.stream_mangement.services;

import com.wolfott.stream_mangement.responses.SerieCompactResponse;
import com.wolfott.stream_mangement.responses.SerieDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface SerieService {
    SerieDetailResponse getOne(Long id);
    List<SerieCompactResponse> getAll();
    List<SerieCompactResponse> getAll(Map<String, Object> filters);
    Page<SerieCompactResponse> getAll(Pageable pageable);
    Page<SerieCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);
}
