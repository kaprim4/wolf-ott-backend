package com.wolfott.mangement.line.controllers;

import com.wolfott.mangement.line.dto.LineCompact;
import com.wolfott.mangement.line.dto.PageRequestDto;
import com.wolfott.mangement.line.requests.LineCreateRequest;
import com.wolfott.mangement.line.requests.LineUpdateRequest;
import com.wolfott.mangement.line.responses.*;
import com.wolfott.mangement.line.services.LineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/lines")
public class LineController {

    @Autowired
    LineService lineService;

    @GetMapping("/{id}")
    public LineDetailResponse getOne(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/all")
    public Page<LineCompact> getAll(@RequestBody PageRequestDto dto) {
        log.info("Get all lines");
        log.info("pageable: {}", dto.getPageable(dto));
        return lineService.getAllLines(dto.getPageable(dto));
    }

    @PostMapping
    public LineCreateResponse createOne(@RequestBody LineCreateRequest request){
        return null;
    }

    @PutMapping("/{id}")
    public LineUpdateResponse updateOne(@PathVariable("id") Long id, @RequestBody LineUpdateRequest request){
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        lineService.delete(id);
    }

}
