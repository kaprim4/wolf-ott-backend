package com.wolfott.stream_mangement.mappers;

import com.wolfott.stream_mangement.models.StreamCategory;
import com.wolfott.stream_mangement.requests.CategoryCreateRequest;
import com.wolfott.stream_mangement.requests.CategoryUpdateRequest;
import com.wolfott.stream_mangement.responses.CategoryCompactResponse;
import com.wolfott.stream_mangement.responses.CategoryCreateResponse;
import com.wolfott.stream_mangement.responses.CategoryDetailResponse;
import com.wolfott.stream_mangement.responses.CategoryUpdateResponse;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CategoryMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public CategoryMapper(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void setupMappings() {
//
    }


    private Converter<String, Integer> lastActivityArrayToActiveConverter() {
        return context -> Optional.ofNullable(context.getSource())
                .filter(lastActivityArray -> !lastActivityArray.isEmpty())
                .map(lastActivityArray -> 1)
                .orElse(0);
    }

    private Converter<Long, Date> expDateToDateConverter() {
        return context -> Optional.ofNullable(context.getSource())
                .map(Date::new)
                .orElse(null);
    }

    // Convert Category entity to CategoryCompactResponse
    public CategoryCompactResponse toCategoryCompactResponse(StreamCategory category) {
        return modelMapper.map(category, CategoryCompactResponse.class);
    }
    // Convert Category entity to CategoryDetailResponse
    public CategoryDetailResponse toCategoryDetailResponse(StreamCategory category) {
        return modelMapper.map(category, CategoryDetailResponse.class);
    }

    // Convert Category entity to CategoryCreateResponse
    public CategoryCreateResponse toCategoryCreateResponse(StreamCategory category) {
        return modelMapper.map(category, CategoryCreateResponse.class);
    }

    // Convert Category entity to CategoryUpdateResponse
    public CategoryUpdateResponse toCategoryUpdateResponse(StreamCategory category) {
        return modelMapper.map(category, CategoryUpdateResponse.class);
    }

    // Convert CategoryCreateRequest to Category entity
    public StreamCategory toCategory(CategoryCreateRequest request) {
        return modelMapper.map(request, StreamCategory.class);
    }
    // Convert CategoryUpdateRequest to Category entity and merge with existing entity
    public StreamCategory mergeCategory(CategoryUpdateRequest request, StreamCategory existingCategory) {
        // Create a copy of the existing line and update its properties
        Long id = existingCategory.getId();
        StreamCategory updatedCategory = modelMapper.map(existingCategory, StreamCategory.class);
        modelMapper.map(request, updatedCategory);
        updatedCategory.setId(id);
        return updatedCategory;
    }
    // Convert Page<Category> to Page<CategoryCompactResponse>
    public Page<CategoryCompactResponse> toCategoryCompactResponsePage(Page<StreamCategory> categoryPage) {
        return new PageImpl<>(
                categoryPage.getContent().stream()
                        .map(this::toCategoryCompactResponse)
                        .collect(Collectors.toList()),
                categoryPage.getPageable(),
                categoryPage.getTotalElements()
        );
    }

    public List<CategoryCompactResponse> toCategoryCompactResponsePage(List<StreamCategory> list) {
        return list.stream().map(this::toCategoryCompactResponse).toList();
    }
}
