package com.wolfott.mangement.line.controllers;

import com.wolfott.mangement.line.requests.PresetCreateRequest;
import com.wolfott.mangement.line.requests.PresetUpdateRequest;
import com.wolfott.mangement.line.responses.*;
import com.wolfott.mangement.line.services.PresetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/presets")
public class PresetController {
    @Autowired
    PresetService presetService;

    @GetMapping("/{id}")
    public PresetDetailResponse getOne(@PathVariable Long id) {
        return presetService.getOne(id);
    }

    @GetMapping("/{id}/bouquets")
    public List<BouquetCompactResponse> getPresetBouquets(@PathVariable Long id) {
        return presetService.getPresetBouquets(id);
    }

    @GetMapping
    public Page<PresetCompactResponse> getAll(@RequestParam Map<String, Object> filters, Pageable pageable) {
        return presetService.getAll(filters, pageable);
    }

    @GetMapping("/list")
    public List<PresetCompactResponse> getAll(@RequestParam Map<String, Object> filters) {
        return presetService.getAll(filters);
    }

    @PostMapping
    public PresetCreateResponse createOne(@RequestBody PresetCreateRequest request){
        return presetService.create(request);
    }

    @PutMapping("/{id}")
    public PresetUpdateResponse updateOne(@PathVariable("id") Long id, @RequestBody PresetUpdateRequest request){
        return presetService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        presetService.delete(id);
    }

}
