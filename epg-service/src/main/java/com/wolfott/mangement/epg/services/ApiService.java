package com.wolfott.mangement.epg.services;

import com.wolfott.mangement.epg.requests.ApiCreateRequest;
import com.wolfott.mangement.epg.requests.ApiUpdateRequest;
import com.wolfott.mangement.epg.responses.ApiCompactResponse;
import com.wolfott.mangement.epg.responses.ApiCreateResponse;
import com.wolfott.mangement.epg.responses.ApiDetailResponse;
import com.wolfott.mangement.epg.responses.ApiUpdateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ApiService {
    ApiDetailResponse getOne(Long id);
    Page<ApiCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);
    ApiCreateResponse create(ApiCreateRequest request);
    ApiUpdateResponse update(Long id, ApiUpdateRequest request);
    void delete(Long id);
}
