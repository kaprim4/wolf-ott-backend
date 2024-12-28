package com.wolfott.mangement.line.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolfott.mangement.line.models.Bouquet;
import com.wolfott.mangement.line.models.PresetBouquetCategory;
import com.wolfott.mangement.line.models.User;
import com.wolfott.mangement.line.requests.BouquetCreateRequest;
import com.wolfott.mangement.line.requests.BouquetUpdateRequest;
import com.wolfott.mangement.line.requests.PresetBouquetCategoryCreateRequest;
import com.wolfott.mangement.line.responses.*;
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
public class BouquetMapper {
    @Autowired
    ModelMapper mapper;

    @PostConstruct
    private void setupMappings(){
        mapper.addMappings(new PropertyMap<Bouquet, BouquetCompactResponse>() {

            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setBouquetName(source.getBouquetName());
                map().setBouquetOrder(source.getBouquetOrder());

                using(countJsonArray()).map(source.getBouquetChannels(), destination.getStreams());
                using(countJsonArray()).map(source.getBouquetMovies(), destination.getMovies());
                using(countJsonArray()).map(source.getBouquetSeries(), destination.getSeries());
                using(countJsonArray()).map(source.getBouquetRadios(), destination.getStations());

            }
        });

        mapper.addMappings(new PropertyMap<Bouquet, BouquetDetailResponse>() {

            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setBouquetName(source.getBouquetName());
                map().setBouquetOrder(source.getBouquetOrder());

                using(jsonToList()).map(source.getBouquetChannels(), destination.getStreams());
                using(jsonToList()).map(source.getBouquetMovies(), destination.getMovies());
                using(jsonToList()).map(source.getBouquetSeries(), destination.getSeries());
                using(jsonToList()).map(source.getBouquetRadios(), destination.getStations());

            }
        });

        mapper.addMappings(new PropertyMap<PresetBouquetCategory, PresetBouquetCategoryCreateResponse>() {

            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setPresetName(source.getPresetName());
                map().setPresetDescription(source.getPresetDescription());
                map().setOwnerId(source.getOwnerId());

                using(bouquetToBouquetIdConverter()).map(source.getBouquet(), destination.getBouquetId());
                using(jsonToList()).map(source.getCategories(), destination.getCategories());

            }
        });
        mapper.addMappings(new PropertyMap<PresetBouquetCategory, PresetBouquetCategoryDetailResponse>() {

            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setPresetName(source.getPresetName());
                map().setPresetDescription(source.getPresetDescription());
                map().setOwnerId(source.getOwnerId());

                using(bouquetToBouquetIdConverter()).map(source.getBouquet(), destination.getBouquetId());
                using(jsonToList()).map(source.getCategories(), destination.getCategories());

            }
        });

        mapper.addMappings(new PropertyMap<PresetBouquetCategoryCreateRequest, PresetBouquetCategory>() {

            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setPresetName(source.getPresetName());
                map().setPresetDescription(source.getPresetDescription());

                using(bouquetIdToBouquetConverter()).map(source.getBouquetId(), destination.getBouquet());
                using(listToJson()).map(source.getCategories(), destination.getCategories());

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

    private Converter<Long, Bouquet> bouquetIdToBouquetConverter() {
        return context -> Optional.ofNullable(context.getSource())
                .map(Bouquet::new)
                .map(bouquet -> {
                    log.info("Bouquet initiated: {}", bouquet);
                    return bouquet;
                })
                .orElseGet(() -> {
                    log.info("Bouquet initiation failed: source is null");
                    return null;
                });
    }

    private Converter<Bouquet, Long> bouquetToBouquetIdConverter() {
        return context -> Optional.ofNullable(context.getSource())
                .map(Bouquet::getId)
                .orElse(null); // "Anonymous"
    }
    private Converter<String, Integer> countJsonArray(){
        return context -> Optional.ofNullable(context.getSource()).stream().map(this::countItems).findAny().orElse(0);
    }
    private Integer countItems(String jsonArray) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (jsonArray == null || jsonArray.trim().isEmpty() || jsonArray.equals("[]")) {
            return 0;
        }

        try {
            JsonNode node = objectMapper.readTree(jsonArray);
            return node.size(); // Get the count of elements in the JSON array
        } catch (Exception e) {
            // Handle the exception (log it, rethrow it, etc.)
            return 0;
        }
    }

    public Bouquet toBouquet(BouquetCreateRequest request){
        return mapper.map(request, Bouquet.class);
    }
    public Bouquet toBouquet(BouquetUpdateRequest request){
        return mapper.map(request, Bouquet.class);
    }

    public BouquetCreateResponse toCreateResponse(Bouquet model){
        return mapper.map(model, BouquetCreateResponse.class);
    }

    public BouquetUpdateResponse toUpdateResponse(Bouquet model){
        return mapper.map(model, BouquetUpdateResponse.class);
    }

    public BouquetDetailResponse toDetailResponse(Bouquet model){
        return mapper.map(model, BouquetDetailResponse.class);
    }

    public BouquetCompactResponse toCompactResponse(Bouquet model){
        return mapper.map(model, BouquetCompactResponse.class);
    }

    public Page<BouquetCompactResponse> toCompactResponse(Page<Bouquet> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    public Collection<BouquetCompactResponse> toCompactResponse(Collection<Bouquet> collection) {
        return collection.stream().map(this::toCompactResponse).collect(Collectors.toList());
    }

    public List<BouquetCompactResponse> toCompactResponse(List<Bouquet> collection) {
        return collection.stream().map(this::toCompactResponse).collect(Collectors.toList());
    }

    public PresetBouquetCategory requestToModel(PresetBouquetCategoryCreateRequest request){
        return this.mapper.map(request, PresetBouquetCategory.class);
    }
    public PresetBouquetCategoryCreateResponse modelToCreateResponse(PresetBouquetCategory model){
        return this.mapper.map(model, PresetBouquetCategoryCreateResponse.class);
    }
    public PresetBouquetCategoryDetailResponse modelToDetailResponse(PresetBouquetCategory model){
        return this.mapper.map(model, PresetBouquetCategoryDetailResponse.class);
    }
}
