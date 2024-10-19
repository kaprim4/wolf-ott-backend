package com.wolfott.stream_mangement.controllers;

import com.wolfott.stream_mangement.responses.SerieCompactResponse;
import com.wolfott.stream_mangement.responses.SerieDetailResponse;
import com.wolfott.stream_mangement.services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/series")
public class SerieController {
    @Autowired
    private SerieService serieService;

    @GetMapping("/{id}")
    public SerieDetailResponse getOne(@PathVariable("id") Long id) {
        return serieService.getOne(id);
    }

    @GetMapping("/list")
    public List<SerieCompactResponse> getAll() {
        return serieService.getAll();
    }
    @GetMapping
    public Page<SerieCompactResponse> getAll(@RequestParam Map<String, Object> filters, Pageable pageable) {
        return serieService.getAll(filters, pageable);
    }
}
