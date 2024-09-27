package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.requests.PresetCreateRequest;
import com.wolfott.mangement.line.requests.PresetUpdateRequest;
import com.wolfott.mangement.line.responses.PresetCompactResponse;
import com.wolfott.mangement.line.responses.PresetCreateResponse;
import com.wolfott.mangement.line.responses.PresetDetailResponse;
import com.wolfott.mangement.line.responses.PresetUpdateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PresetService {
    PresetDetailResponse getOne(Long id);
    List<PresetCompactResponse> getAll(Map<String, Object> filters);
    Page<PresetCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);
    PresetCreateResponse create(PresetCreateRequest request);
    PresetUpdateResponse update(final Long id, PresetUpdateRequest request);
    void delete(Long id);
}
