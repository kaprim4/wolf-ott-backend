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

    //    CHANNEL SECTION

    @GetMapping("/{epg_id}/channels/{channel_id}")
    public ChannelDetailResponse getOne(@PathVariable("epg_id") Long epgId, @PathVariable("channel_id") Long channelId){
        return epgService.getChannel(epgId, channelId);
    }

    @GetMapping("/{epg_id}/channels")
    public Page<ChannelCompactResponse> getAll(@PathVariable("epg_id") Long epgId, @RequestParam Map<String, Object> filters, Pageable pageable){
        return epgService.getChannels(epgId, filters, pageable);
    }

    //    DATA SECTION

    @GetMapping("/{epg_id}/datas/{data_id}")
    public DataDetailResponse getEpgData(@PathVariable("epg_id") Long epgId, @PathVariable("data_id") Long dataId){
        return epgService.getData(epgId, dataId);
    }

    @GetMapping("/{epg_id}/datas")
    public Page<DataCompactResponse> getAllEpgData(@PathVariable("epg_id") Long epgId, @RequestParam Map<String, Object> filters, Pageable pageable){
        return epgService.getData(epgId, filters, pageable);
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
