package com.wolfott.mangement.epg.services;

import com.wolfott.mangement.epg.requests.LanguageCreateRequest;
import com.wolfott.mangement.epg.requests.LanguageUpdateRequest;
import com.wolfott.mangement.epg.responses.LanguageCompactResponse;
import com.wolfott.mangement.epg.responses.LanguageCreateResponse;
import com.wolfott.mangement.epg.responses.LanguageDetailResponse;
import com.wolfott.mangement.epg.responses.LanguageUpdateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface LanguageService {
    LanguageDetailResponse getOne(Long id);
    Page<LanguageCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);
    LanguageCreateResponse create(LanguageCreateRequest request);
    LanguageUpdateResponse update(Long id, LanguageUpdateRequest request);
    void delete(Long id);
}
