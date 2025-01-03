package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.models.Line;
import com.wolfott.mangement.line.models.LineList;
import com.wolfott.mangement.line.requests.LineCreateRequest;
import com.wolfott.mangement.line.requests.LineUpdateRequest;
import com.wolfott.mangement.line.requests.PatchRequest;
import com.wolfott.mangement.line.responses.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface LineService {

    LineDetailResponse getOne(Long id);

    List<BouquetCompactResponse> getLineBouquets(Long id);

    int getLinesCount();

    int getLastWeekCount();

    int getCountByMemberId(Long id);

    Map<String, Long> getCreatedLinesLastSixMonths();

    List<LineCompactResponse> getLastRegisteredLines();

    List<LineCompactResponse> getAll(Map<String, Object> filters);

    Page<LineCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);

    Page<LineList> getAllforListing(Map<String, Object> filters, Pageable pageable);

    LineCreateResponse create(LineCreateRequest request);

    LineUpdateResponse update(Long id, LineUpdateRequest request);

    LinePatchResponse update(Long id, PatchRequest request);

    void delete(Long id);

    void changeVPN(Long id);

    Line changeVPN(Line line);

    LineUpdateResponse suspendLine(Long id);

    LineUpdateResponse disableLine(Long id);

    void killLineLives(Long id);

    void killLineConnections(Long id);

    List<Line> getLinesByPresetId(Long id);

    void saveAll(List<Line> lineList);

    List<LineCompactResponse> getExpiredLine(Long limit);
}
