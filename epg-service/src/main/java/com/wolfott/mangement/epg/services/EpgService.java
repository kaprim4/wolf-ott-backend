package com.wolfott.mangement.epg.services;

import com.wolfott.mangement.epg.requests.EpgCreateRequest;
import com.wolfott.mangement.epg.requests.EpgUpdateRequest;
import com.wolfott.mangement.epg.responses.EpgCompactResponse;
import com.wolfott.mangement.epg.responses.EpgCreateResponse;
import com.wolfott.mangement.epg.responses.EpgDetailResponse;
import com.wolfott.mangement.epg.responses.EpgUpdateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;


public interface EpgService {
    EpgDetailResponse getOne(Long id);
    Page<EpgCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);
    EpgCreateResponse create(EpgCreateRequest request);
    EpgUpdateResponse update(Long id, EpgUpdateRequest request);
    void delete(Long id);
}
