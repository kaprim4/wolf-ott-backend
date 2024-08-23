package com.wolfott.mangement.epg.controllers;

import com.wolfott.mangement.epg.requests.EpgCreateRequest;
import com.wolfott.mangement.epg.requests.EpgUpdateRequest;
import com.wolfott.mangement.epg.responses.EpgCompactResponse;
import com.wolfott.mangement.epg.responses.EpgCreateResponse;
import com.wolfott.mangement.epg.responses.EpgDetailResponse;
import com.wolfott.mangement.epg.responses.EpgUpdateResponse;
import com.wolfott.mangement.epg.services.EpgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/epgs")
public class EpgController {

    @Autowired
    EpgService epgService;

    @GetMapping("/{id}")
    public EpgDetailResponse getOne(@PathVariable("id") Long id){
        return epgService.getOne(id);
    }

    @GetMapping
    public Page<EpgCompactResponse> getAll(@RequestParam Map<String, Object> filters, Pageable pageable){
        return epgService.getAll(filters, pageable);
    }

    @PostMapping
    public EpgCreateResponse create(@RequestBody EpgCreateRequest request){
        return epgService.create(request);
    }

    @PutMapping("/{id}")
    public EpgUpdateResponse update(@PathVariable("id") Long id, @RequestBody EpgUpdateRequest request){
        return epgService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        epgService.delete(id);
    }

}
