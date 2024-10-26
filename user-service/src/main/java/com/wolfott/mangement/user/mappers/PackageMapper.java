package com.wolfott.mangement.user.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolfott.mangement.user.models.UserPackage;
import com.wolfott.mangement.user.requests.PackageCreateRequest;
import com.wolfott.mangement.user.requests.PackageUpdateRequest;
import com.wolfott.mangement.user.responses.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PackageMapper {
    @Autowired
    ModelMapper mapper;

    @PostConstruct
    private void setupMappings(){
        mapper.addMappings(new PropertyMap<UserPackage, PackageDetailResponse>() {
            @Override
            protected void configure() {
                using(jsonToList()).map(source.getBouquets(), destination.getBouquets());
                using(jsonToList()).map(source.getGroups(), destination.getGroups());
                using(jsonToList()).map(source.getOutputFormats(), destination.getOutputFormats());
            }
        });

        mapper.addMappings(new PropertyMap<UserPackage, PackageCompactResponse>() {
            @Override
            protected void configure() {
                using(jsonToList()).map(source.getBouquets(), destination.getBouquets());
                using(jsonToList()).map(source.getGroups(), destination.getGroups());
                using(jsonToList()).map(source.getOutputFormats(), destination.getOutputFormats());
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

    private Converter<List, String> listToJson() {
        return context -> {
            List<?> list = context.getSource();
            if (list == null) return null; // Return null for a null input
            try {
                return new ObjectMapper().writeValueAsString(list);
            } catch (JsonProcessingException e) {
                log.error("Error converting list to JSON: {}", list, e);
                return null; // Or handle it as needed, e.g., return an empty string or a specific error message
            }
        };
    }


    public UserPackage toPackage(PackageCreateRequest request){
        return mapper.map(request, UserPackage.class);
    }
    public UserPackage toPackage(PackageUpdateRequest request){
        return mapper.map(request, UserPackage.class);
    }

    public PackageCreateResponse toCreateResponse(UserPackage model){
        return mapper.map(model, PackageCreateResponse.class);
    }

    public PackageUpdateResponse toUpdateResponse(UserPackage model){
        return mapper.map(model, PackageUpdateResponse.class);
    }

    public PackageDetailResponse toDetailResponse(UserPackage model){
        return mapper.map(model, PackageDetailResponse.class);
    }

    public PackageCompactResponse toCompactResponse(UserPackage model){
        return mapper.map(model, PackageCompactResponse.class);
    }

    public Page<PackageCompactResponse> toCompactResponse(Page<UserPackage> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    public Collection<PackageCompactResponse> toCompactResponse(Collection<UserPackage> collection) {
        return collection.stream().map(this::toCompactResponse).collect(Collectors.toList());
    }

    public List<PackageCompactResponse> toCompactResponse(List<UserPackage> collection) {
        return collection.stream().map(this::toCompactResponse).collect(Collectors.toList());
    }
}
