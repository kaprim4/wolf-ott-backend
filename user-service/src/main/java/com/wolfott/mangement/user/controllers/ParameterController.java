package com.wolfott.mangement.user.controllers;

import com.wolfott.mangement.user.models.Parameter;
import com.wolfott.mangement.user.services.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/parameters")
public class ParameterController {
    @Autowired
    private ParameterService parameterService;

    @GetMapping
    public Page<Parameter> getAll(Pageable pageable){
        return parameterService.getAll(pageable);
    }
}
