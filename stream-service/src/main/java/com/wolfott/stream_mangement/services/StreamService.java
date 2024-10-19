package com.wolfott.stream_mangement.services;

import com.wolfott.stream_mangement.models.StreamType;
import com.wolfott.stream_mangement.responses.StreamCompactResponse;
import com.wolfott.stream_mangement.responses.StreamDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface StreamService {
    StreamDetailResponse getOne(Long id);
    List<StreamCompactResponse> getAll();
    List<StreamCompactResponse> getAll(Map<String, Object> filters);
    Page<StreamCompactResponse> getAll(Pageable pageable);
    Page<StreamCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);
    Page<StreamCompactResponse> getAll(String type, Map<String, Object> filters, Pageable pageable);
    Page<StreamCompactResponse> getAllByType(String type, Pageable pageable);
    Page<StreamCompactResponse> getAllByType(String type, Map<String, Object> filters, Pageable pageable);

    List<StreamType> getTypes();
}
