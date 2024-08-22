package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.requests.BouquetCreateRequest;
import com.wolfott.mangement.line.requests.BouquetUpdateRequest;
import com.wolfott.mangement.line.responses.BouquetCompactResponse;
import com.wolfott.mangement.line.responses.BouquetCreateResponse;
import com.wolfott.mangement.line.responses.BouquetDetailResponse;
import com.wolfott.mangement.line.responses.BouquetUpdateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface BouquetService {
    BouquetDetailResponse getOne(Long id);
    Page<BouquetCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);
    BouquetCreateResponse create(BouquetCreateRequest request);
    BouquetUpdateResponse update(Long id, BouquetUpdateRequest request);
    void delete(Long id);
}
