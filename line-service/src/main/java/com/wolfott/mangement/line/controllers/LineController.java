package com.wolfott.mangement.line.controllers;

import com.wolfott.mangement.line.requests.LineCreateRequest;
import com.wolfott.mangement.line.requests.LineUpdateRequest;
import com.wolfott.mangement.line.requests.PatchRequest;
import com.wolfott.mangement.line.responses.*;
import com.wolfott.mangement.line.services.LineActivityService;
import com.wolfott.mangement.line.services.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/lines")
public class LineController {

    @Autowired
    LineService lineService;

    @Autowired
    LineActivityService activityService;


    @GetMapping("/{id}")
    public LineDetailResponse getOne(@PathVariable Long id) {
        return lineService.getOne(id);
    }

    @GetMapping("/{id}/bouquets")
    public List<BouquetCompactResponse> getLineBouquets(@PathVariable Long id) {
        return lineService.getLineBouquets(id);
    }

    @GetMapping("/count")
    public int getLinesCount() {
        return lineService.getLinesCount();
    }

    @GetMapping
    public Page<LineCompactResponse> getAll(@RequestParam Map<String, Object> filters, Pageable pageable) {
        return lineService.getAll(filters, pageable);
    }

    @GetMapping("/list")
    public List<LineCompactResponse> getAll(@RequestParam Map<String, Object> filters) {
        return lineService.getAll(filters);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<Integer> getCountByMemberId(@PathVariable Long id) {
        int count = lineService.getCountByMemberId(id);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/last-registered")
    public ResponseEntity<List<LineCompactResponse>> getLastRegisteredLines() {
        List<LineCompactResponse> lines = lineService.getLastRegisteredLines();
        return ResponseEntity.ok(lines);
    }

    @GetMapping("/last-week-count")
    public ResponseEntity<Integer> getLastWeekCount() {
        int count = lineService.getLastWeekCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/created-last-six-months")
    public ResponseEntity<Map<String, Long>> getCreatedLinesLastSixMonths() {
        Map<String, Long> createdCounts = lineService.getCreatedLinesLastSixMonths();
        return ResponseEntity.ok(createdCounts);
    }

    @PostMapping
    public LineCreateResponse createOne(@RequestBody LineCreateRequest request) {
        return lineService.create(request);
    }

    @PutMapping("/{id}")
    public LineUpdateResponse updateOne(@PathVariable("id") Long id, @RequestBody LineUpdateRequest request) {
        return lineService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        lineService.delete(id);
    }

    @GetMapping("/activities")
    public Page<LineActivityCompactResponse> getAllActivities(Pageable pageable) {
        return activityService.getAll(pageable);
    }


    @PatchMapping("/{id}")
    public LinePatchResponse update(@PathVariable("id") Long id, @RequestBody PatchRequest request) {
        return lineService.update(id, request);
    }

    @PostMapping("/{id}/vpn/refresh")
    public void refresh(@PathVariable("id") Long id) {
        lineService.changeVPN(id);
    }

}
