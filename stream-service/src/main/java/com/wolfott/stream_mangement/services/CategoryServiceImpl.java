package com.wolfott.stream_mangement.services;

import com.wolfott.stream_mangement.exceptions.CategoryNotFoundException;
import com.wolfott.stream_mangement.mappers.CategoryMapper;
import com.wolfott.stream_mangement.models.StreamCategory;
import com.wolfott.stream_mangement.repositories.CategoryRepository;
import com.wolfott.stream_mangement.requests.CategoryCreateRequest;
import com.wolfott.stream_mangement.requests.CategoryUpdateRequest;
import com.wolfott.stream_mangement.responses.CategoryCompactResponse;
import com.wolfott.stream_mangement.responses.CategoryCreateResponse;
import com.wolfott.stream_mangement.responses.CategoryDetailResponse;
import com.wolfott.stream_mangement.responses.CategoryUpdateResponse;
import com.wolfott.stream_mangement.specifications.CategorySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategorySpecification categorySpecification;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryDetailResponse getOne(Long id) {
        StreamCategory category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        return categoryMapper.toCategoryDetailResponse(category);
    }

    @Override
    public List<CategoryCompactResponse> getAll() {
        List<StreamCategory> categories = categoryRepository.findAll();
        return categoryMapper.toCategoryCompactResponsePage(categories);
    }

    @Override
    public List<CategoryCompactResponse> getAll(Map<String, Object> filters) {
        Specification<StreamCategory> spec = categorySpecification.dynamic(filters);
        List<StreamCategory> categories = categoryRepository.findAll(spec);
        return categoryMapper.toCategoryCompactResponsePage(categories);
    }

    @Override
    public Page<CategoryCompactResponse> getAll(Pageable pageable) {
        Page<StreamCategory> categories = categoryRepository.findAll(pageable);
        return categoryMapper.toCategoryCompactResponsePage(categories);
    }

    @Override
    public Page<CategoryCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<StreamCategory> spec = categorySpecification.dynamic(filters);
        Page<StreamCategory> categories = categoryRepository.findAll(spec, pageable);
        return categoryMapper.toCategoryCompactResponsePage(categories);
    }

    @Override
    public CategoryCreateResponse create(CategoryCreateRequest request) {
        StreamCategory category = categoryMapper.toCategory(request);
        category = categoryRepository.save(category);
        return categoryMapper.toCategoryCreateResponse(category);
    }

    @Override
    public CategoryUpdateResponse update(Long id, CategoryUpdateRequest request) {
        StreamCategory category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        category = categoryMapper.mergeCategory(request, category);
        category = categoryRepository.save(category);
        return categoryMapper.toCategoryUpdateResponse(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
