package com.wolfott.mangement.line.mappers;

import com.wolfott.mangement.line.models.StreamCategory;
import com.wolfott.mangement.line.responses.CategoryCompactResponse;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
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

    public List<CategoryCompactResponse> toCategoryCompactResponse(Collection<StreamCategory> categories) {
        return categories.stream().map(this::toCategoryCompactResponse).collect(Collectors.toList());
    }

    
}
