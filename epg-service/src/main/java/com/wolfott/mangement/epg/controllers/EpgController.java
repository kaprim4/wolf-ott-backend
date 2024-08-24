package com.wolfott.mangement.epg.controllers;

import com.wolfott.mangement.epg.requests.*;
import com.wolfott.mangement.epg.responses.*;
import com.wolfott.mangement.epg.services.ApiService;
import com.wolfott.mangement.epg.services.EpgService;
import com.wolfott.mangement.epg.services.LanguageService;
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
    @Autowired
    ApiService apiService;
    @Autowired
    LanguageService languageService;

//    EPG SECTION

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

//    API SECTION

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        epgService.delete(id);
    }

    @GetMapping("/apis/{id}")
    public ApiDetailResponse getApi(@PathVariable("id") Long id){
        return apiService.getOne(id);
    }

    @GetMapping("/apis")
    public Page<ApiCompactResponse> getApis(@RequestParam Map<String, Object> filters, Pageable pageable){
        return apiService.getAll(filters, pageable);
    }

    @PostMapping("/apis")
    public ApiCreateResponse createApi(@RequestBody ApiCreateRequest request){
        return apiService.create(request);
    }

    @PutMapping("/apis/{id}")
    public ApiUpdateResponse updateApi(@PathVariable("id") Long id, @RequestBody ApiUpdateRequest request){
        return apiService.update(id, request);
    }

    @DeleteMapping("/apis/{id}")
    public void deleteApi(@PathVariable("id") Long id){
        apiService.delete(id);
    }

//    LANGUAGE SECTION

    @GetMapping("/languages/{id}")
    public LanguageDetailResponse getLanguage(@PathVariable("id") Long id){
        return languageService.getOne(id);
    }

    @GetMapping("/languages")
    public Page<LanguageCompactResponse> getLanguages(@RequestParam Map<String, Object> filters, Pageable pageable){
        return languageService.getAll(filters, pageable);
    }

    @PostMapping("/languages")
    public LanguageCreateResponse createLanguage(@RequestBody LanguageCreateRequest request){
        return languageService.create(request);
    }

    @PutMapping("/languages/{id}")
    public LanguageUpdateResponse updateLanguage(@PathVariable("id") Long id, @RequestBody LanguageUpdateRequest request){
        return languageService.update(id, request);
    }

    @DeleteMapping("/languages/{id}")
    public void deleteLanguage(@PathVariable("id") Long id){
        languageService.delete(id);
    }
}
