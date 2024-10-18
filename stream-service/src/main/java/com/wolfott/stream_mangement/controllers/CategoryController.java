package com.wolfott.stream_mangement.controllers;

import com.wolfott.stream_mangement.requests.CategoryCreateRequest;
import com.wolfott.stream_mangement.requests.CategoryUpdateRequest;
import com.wolfott.stream_mangement.responses.CategoryCompactResponse;
import com.wolfott.stream_mangement.responses.CategoryCreateResponse;
import com.wolfott.stream_mangement.responses.CategoryDetailResponse;
import com.wolfott.stream_mangement.responses.CategoryUpdateResponse;
import com.wolfott.stream_mangement.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryDetailResponse getOne(@PathVariable("id") Long id){
        return categoryService.getOne(id);
    }

    @GetMapping("/list")
    public List<CategoryCompactResponse> getAll() {
        return categoryService.getAll();
    }

    @GetMapping
    public Page<CategoryCompactResponse> getAll(@RequestParam Map<String, Object> filters, Pageable pageable) {
        return categoryService.getAll(filters, pageable);
    }

    @PostMapping
    public CategoryCreateResponse create(@RequestBody CategoryCreateRequest request){
        return categoryService.create(request);
    }

    @PostMapping("/{id}")
    public CategoryUpdateResponse update(@PathVariable("id") Long id, @RequestBody CategoryUpdateRequest request){
        return categoryService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        categoryService.delete(id);
    }
}
