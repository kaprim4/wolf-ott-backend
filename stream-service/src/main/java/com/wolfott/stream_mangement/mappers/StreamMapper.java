package com.wolfott.stream_mangement.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolfott.stream_mangement.models.Stream;
import com.wolfott.stream_mangement.responses.StreamCompactResponse;
import com.wolfott.stream_mangement.responses.StreamDetailResponse;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class StreamMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public StreamMapper(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void setupMappings() {
        modelMapper.addMappings(new PropertyMap<Stream, StreamCompactResponse>() {
            @Override
            protected void configure() {
                using(jsonToList()).map(source.getCategoryId(), destination.getCategories());
            }
        });

        modelMapper.addMappings(new PropertyMap<Stream, StreamDetailResponse>() {
            @Override
            protected void configure() {
                using(jsonToList()).map(source.getCategoryId(), destination.getCategories());
            }
        });
    }

    private Converter<String, List> jsonToList(){
        return context -> {
            String json = context.getSource();
            if (json == null) return Collections.emptyList();
            try {
                return new ObjectMapper().readValue(json, new TypeReference<List>() {});
            } catch (JsonProcessingException e) {
                log.error("Error parsing JSON to list: {}", json, e);
                return Collections.emptyList();
            }
        };
    }

    private Converter<String, Map<String, Object>> jsonToMap() {
        return context -> {
            String json = context.getSource();
            if (json == null) return Collections.emptyMap();
            try {
                return new ObjectMapper().readValue(json, new TypeReference<Map<String, Object>>() {});
            } catch (JsonProcessingException e) {
                log.error("Error parsing JSON to map: {}", json, e);
                return Collections.emptyMap();
            }
        };
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

    // Convert Stream entity to CategoryCompactResponse
    public StreamCompactResponse toStreamCompactResponse(Stream stream) {
        return modelMapper.map(stream, StreamCompactResponse.class);
    }
    // Convert Stream entity to StreamDetailResponse
    public StreamDetailResponse toStreamDetailResponse(Stream category) {
        return modelMapper.map(category, StreamDetailResponse.class);
    }

    // Convert Page<Stream> to Page<StreamCompactResponse>
    public Page<StreamCompactResponse> toStreamCompactResponsePage(Page<Stream> streamPage) {
        return new PageImpl<>(
                streamPage.getContent().stream()
                        .map(this::toStreamCompactResponse)
                        .collect(Collectors.toList()),
                streamPage.getPageable(),
                streamPage.getTotalElements()
        );
    }

    public List<StreamCompactResponse> toStreamCompactResponseList(List<Stream> list) {
        return list.stream().map(this::toStreamCompactResponse).toList();
    }
}
