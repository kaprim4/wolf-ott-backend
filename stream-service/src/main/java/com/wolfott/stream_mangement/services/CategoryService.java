package com.wolfott.stream_mangement.services;

import com.wolfott.stream_mangement.requests.CategoryCreateRequest;
import com.wolfott.stream_mangement.requests.CategoryUpdateRequest;
import com.wolfott.stream_mangement.responses.CategoryCompactResponse;
import com.wolfott.stream_mangement.responses.CategoryCreateResponse;
import com.wolfott.stream_mangement.responses.CategoryDetailResponse;
import com.wolfott.stream_mangement.responses.CategoryUpdateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    CategoryDetailResponse getOne(Long id);
    List<CategoryCompactResponse> getAll();
    List<CategoryCompactResponse> getAll(Map<String, Object> filters);
    Page<CategoryCompactResponse> getAll(Pageable pageable);
    Page<CategoryCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);
    CategoryCreateResponse create(CategoryCreateRequest request);
    CategoryUpdateResponse update(Long id, CategoryUpdateRequest request);
    void delete(Long id);
}
