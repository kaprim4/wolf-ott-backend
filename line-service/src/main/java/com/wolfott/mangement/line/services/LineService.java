package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.responses.LineCompactResponse;
import com.wolfott.mangement.line.responses.LineDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface LineService {

    LineDetailResponse getOne(Long id);
    public Page<LineCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);
}
