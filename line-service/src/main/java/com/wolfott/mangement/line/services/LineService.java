package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.requests.LineCreateRequest;
import com.wolfott.mangement.line.requests.LineUpdateRequest;
import com.wolfott.mangement.line.responses.LineCompactResponse;
import com.wolfott.mangement.line.responses.LineCreateResponse;
import com.wolfott.mangement.line.responses.LineDetailResponse;
import com.wolfott.mangement.line.responses.LineUpdateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface LineService {

    LineDetailResponse getOne(Long id);

    int getLinesCount();

    Page<LineCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);

    LineCreateResponse create(LineCreateRequest request);

    LineUpdateResponse update(Long id, LineUpdateRequest request);

    void delete(Long id);
}
