package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.models.LineList;
import com.wolfott.mangement.line.requests.LineCreateRequest;
import com.wolfott.mangement.line.requests.LineUpdateRequest;
import com.wolfott.mangement.line.responses.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface LineService {

    LineDetailResponse getOne(Long id);
    List<BouquetCompactResponse> getLineBouquets(Long id);

    int getLinesCount();

    int getLastWeekCount();

    List<LineCompactResponse> getLastRegisteredLines();

    List<LineCompactResponse> getAll(Map<String, Object> filters);

    Page<LineCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);

    Page<LineList> getAllforListing(Map<String, Object> filters, Pageable pageable);

    LineCreateResponse create(LineCreateRequest request);

    LineUpdateResponse update(Long id, LineUpdateRequest request);

    void delete(Long id);


}
