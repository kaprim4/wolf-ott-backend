package com.wolfott.mangement.line.controllers;

import com.wolfott.mangement.line.models.LineList;
import com.wolfott.mangement.line.requests.LineCreateRequest;
import com.wolfott.mangement.line.requests.LineUpdateRequest;
import com.wolfott.mangement.line.responses.*;
import com.wolfott.mangement.line.services.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/lines")
public class LineController {

    @Autowired
    LineService lineService;

    @GetMapping("/{id}")
    public LineDetailResponse getOne(@PathVariable Long id) {
        return lineService.getOne(id);
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
    public Page<LineList> getAllForListing(@RequestParam Map<String, Object> filters, Pageable pageable) {
        return lineService.getAllforListing(filters, pageable);
    }

    @PostMapping
    public LineCreateResponse createOne(@RequestBody LineCreateRequest request){
        return lineService.create(request);
    }

    @PutMapping("/{id}")
    public LineUpdateResponse updateOne(@PathVariable("id") Long id, @RequestBody LineUpdateRequest request){
        return lineService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        lineService.delete(id);
    }

}
