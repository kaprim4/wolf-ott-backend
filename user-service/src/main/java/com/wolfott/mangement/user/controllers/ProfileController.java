package com.wolfott.mangement.user.controllers;

import com.wolfott.mangement.user.requests.ProfileCreateRequest;
import com.wolfott.mangement.user.requests.ProfileUpdateRequest;
import com.wolfott.mangement.user.responses.ProfileCompactResponse;
import com.wolfott.mangement.user.responses.ProfileCreateResponse;
import com.wolfott.mangement.user.responses.ProfileDetailResponse;
import com.wolfott.mangement.user.responses.ProfileUpdateResponse;
import com.wolfott.mangement.user.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping("/{id}")
    public ProfileDetailResponse getOne(@PathVariable("id") Long id){
        return profileService.getOne(id);
    }

    @GetMapping
    public Page<ProfileCompactResponse> getAll(@RequestParam Map<String, Object> filters, Pageable pageable){
        return profileService.getAll(filters, pageable);
    }

    @PostMapping
    public ProfileCreateResponse create(@RequestBody ProfileCreateRequest request){
        return profileService.create(request);
    }

    @PutMapping("/{id}")
    public ProfileUpdateResponse update(@PathVariable("id") Long id, @RequestBody ProfileUpdateRequest request){
        return profileService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        profileService.delete(id);
    }
}
