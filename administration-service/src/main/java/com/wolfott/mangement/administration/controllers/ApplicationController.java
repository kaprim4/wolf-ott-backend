package com.wolfott.mangement.administration.controllers;

import com.wolfott.mangement.administration.models.Application;
import com.wolfott.mangement.administration.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController
{
    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/list")
    public List<Application> getAll() {
        return applicationService.getAll();
    }

    @GetMapping
    public Page<Application> getAll(Pageable pageable) {
        return applicationService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Application getAll(@PathVariable("id") Long id) {
        return applicationService.getOne(id);
    }

    @PostMapping
    public Application create(@RequestBody Application application) {
        return applicationService.create(application);
    }

    @PutMapping("/{id}")
    public Application update(@PathVariable("id") Long id, @RequestBody Application application) {
        return applicationService.update(id, application);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        applicationService.delete(id);
    }
}
