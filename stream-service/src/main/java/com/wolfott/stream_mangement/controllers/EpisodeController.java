package com.wolfott.stream_mangement.controllers;


import com.wolfott.stream_mangement.responses.EpisodeCompactResponse;
import com.wolfott.stream_mangement.responses.EpisodeDetailResponse;
import com.wolfott.stream_mangement.services.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/episodes")
public class EpisodeController {
    @Autowired
    private EpisodeService episodeService;

    @GetMapping("/{id}")
    public EpisodeDetailResponse getOne(@PathVariable("id") Long id) {
        return episodeService.getOne(id);
    }

    @GetMapping("/list")
    public List<EpisodeCompactResponse> getAll() {
        return episodeService.getAll();
    }
    @GetMapping
    public Page<EpisodeCompactResponse> getAll(@RequestParam Map<String, Object> filters, Pageable pageable) {
        return episodeService.getAll(filters, pageable);
    }
}
