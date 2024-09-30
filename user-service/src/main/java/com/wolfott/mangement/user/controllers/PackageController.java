package com.wolfott.mangement.user.controllers;

import com.wolfott.mangement.user.requests.PackageCreateRequest;
import com.wolfott.mangement.user.requests.PackageUpdateRequest;
import com.wolfott.mangement.user.responses.PackageCompactResponse;
import com.wolfott.mangement.user.responses.PackageCreateResponse;
import com.wolfott.mangement.user.responses.PackageDetailResponse;
import com.wolfott.mangement.user.responses.PackageUpdateResponse;
import com.wolfott.mangement.user.services.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/packages")
public class PackageController {

    @Autowired
    PackageService packageService;

    @GetMapping("/{id}")
    public PackageDetailResponse getOne(@PathVariable("id") String id){
        return packageService.getOne(id);
    }

    @GetMapping("list")
    public List<PackageCompactResponse> getAll(@RequestParam Map<String, Object> filters){
        return packageService.getAll(filters);
    }
    @GetMapping
    public Page<PackageCompactResponse> getAll(@RequestParam Map<String, Object> filters, Pageable pageable){
        return packageService.getAll(filters, pageable);
    }

    @PostMapping
    public PackageCreateResponse create(@RequestBody PackageCreateRequest request){
        return packageService.create(request);
    }

    @PutMapping("/{id}")
    public PackageUpdateResponse update(@PathVariable("id") String id, @RequestBody PackageUpdateRequest request){
        return packageService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        packageService.delete(id);
    }
}
