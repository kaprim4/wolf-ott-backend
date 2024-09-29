package com.wolfott.mangement.line.controllers;

import com.wolfott.mangement.line.requests.BouquetCreateRequest;
import com.wolfott.mangement.line.requests.BouquetUpdateRequest;
import com.wolfott.mangement.line.responses.BouquetCompactResponse;
import com.wolfott.mangement.line.responses.BouquetCreateResponse;
import com.wolfott.mangement.line.responses.BouquetDetailResponse;
import com.wolfott.mangement.line.responses.BouquetUpdateResponse;
import com.wolfott.mangement.line.services.BouquetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bouquets")
public class BouquetController {

    @Autowired
    BouquetService bouquetService;

    @GetMapping("/{id}")
    public BouquetDetailResponse getOne(@PathVariable("id") Long id){
        return bouquetService.getOne(id);
    }

    @GetMapping("/list")
    public List<BouquetCompactResponse> getAll(@RequestParam Map<String, Object> filters){
        return bouquetService.getAll(filters);
    }

    @GetMapping
    public Page<BouquetCompactResponse> getAll(@RequestParam Map<String, Object> filters, Pageable pageable){
        return bouquetService.getAll(filters, pageable);
    }

    @PostMapping
    BouquetCreateResponse create(@RequestBody BouquetCreateRequest request){
        return bouquetService.create(request);
    }

    @PutMapping("/{id}")
    public BouquetUpdateResponse update(@PathVariable("id") Long id, @RequestBody BouquetUpdateRequest request){
        return bouquetService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(Long id){
        bouquetService.delete(id);
    }
}
