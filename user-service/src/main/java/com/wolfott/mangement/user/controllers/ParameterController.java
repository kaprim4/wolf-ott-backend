package com.wolfott.mangement.user.controllers;

import com.wolfott.mangement.user.models.Parameter;
import com.wolfott.mangement.user.requests.ParameterCreateRequest;
import com.wolfott.mangement.user.requests.ParameterUpdateRequest;
import com.wolfott.mangement.user.responses.ParameterCompactResponse;
import com.wolfott.mangement.user.responses.ParameterCreateResponse;
import com.wolfott.mangement.user.responses.ParameterDetailResponse;
import com.wolfott.mangement.user.responses.ParameterUpdateResponse;
import com.wolfott.mangement.user.services.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parameters")
public class ParameterController {
    @Autowired
    private ParameterService parameterService;

    @GetMapping
    public Page<ParameterCompactResponse> getAll(Pageable pageable){
        return parameterService.getAll(pageable);
    }

    @GetMapping("{id}")
    public ParameterDetailResponse getOne(@PathVariable("id") Long id){
        return parameterService.getOne(id);
    }

    @GetMapping("@{id}")
    public ParameterDetailResponse getOne(@PathVariable("key") String key){
        return parameterService.getOneByKey(key);
    }

    @PostMapping
    public ParameterCreateResponse create(@RequestBody ParameterCreateRequest request){
        return parameterService.create(request);
    }

    @PutMapping("{id}")
    public ParameterUpdateResponse update(@PathVariable Long id, @RequestBody ParameterUpdateRequest request){
        return parameterService.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        parameterService.delete(id);
    }
}