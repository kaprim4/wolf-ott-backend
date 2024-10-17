package com.wolfott.stream_mangement.services;

import com.wolfott.stream_mangement.responses.StreamCompactResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface StreamService {
    List<StreamCompactResponse> getAll(Map<String, Object> filters);
    Page<StreamCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);
    void delete(Long id);
}
