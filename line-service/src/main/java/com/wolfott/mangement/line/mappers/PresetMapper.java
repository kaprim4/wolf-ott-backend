package com.wolfott.mangement.line.mappers;

import com.wolfott.mangement.line.models.*;
import com.wolfott.mangement.line.requests.PresetCreateRequest;
import com.wolfott.mangement.line.requests.PresetUpdateRequest;
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

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PresetMapper {
    @Autowired
    ModelMapper mapper;

    @PostConstruct
    private void setupMappings() {
        // Mapping from PresetUpdateRequest to Preset
        mapper.addMappings(new PropertyMap<PresetCreateRequest, Preset>() {
            @Override
            protected void configure() {
                map().setPresetName(source.getPresetName());
                map().setPresetDescription(source.getPresetDescription());
                map().setStatus(source.getStatus());
                // Pass the current preset to the converter
                using(idxToBouquetsConverter()).map(source.getBouquets()).setPresetBouquets(null);
            }
        });

        mapper.addMappings(new PropertyMap<PresetUpdateRequest, Preset>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setPresetName(source.getPresetName());
                map().setPresetDescription(source.getPresetDescription());
                map().setStatus(source.getStatus());
                // Pass the current preset to the converter
                using(idxToBouquetsConverter()).map(source.getBouquets()).setPresetBouquets(null);
            }
        });

        // Mapping from Preset to PresetUpdateResponse
        mapper.addMappings(new PropertyMap<Preset, PresetUpdateResponse>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setPresetName(source.getPresetName());
                map().setPresetDescription(source.getPresetDescription());
                map().setStatus(source.getStatus());
                map().setCreatedAt(source.getCreatedAt());
                map().setUpdatedAt(source.getUpdatedAt());
                // Map PresetBouquet entities to bouquet IDs
                using(presetBouquetsToIdsConverter()).map(source.getPresetBouquets()).setBouquets(null);
            }
        });

        // Mapping from Preset to PresetUpdateResponse
        mapper.addMappings(new PropertyMap<Preset, PresetDetailResponse>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setPresetName(source.getPresetName());
                map().setPresetDescription(source.getPresetDescription());
                map().setStatus(source.getStatus());
                map().setCreatedAt(source.getCreatedAt());
                map().setUpdatedAt(source.getUpdatedAt());
                // Map PresetBouquet entities to bouquet IDs
                using(presetBouquetsToIdsConverter()).map(source.getPresetBouquets()).setBouquets(null);
            }
        });

        mapper.addMappings(new PropertyMap<Preset, PresetCompactResponse>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setPresetName(source.getPresetName());
                map().setPresetDescription(source.getPresetDescription());
                map().setStatus(source.getStatus());
                map().setCreatedAt(source.getCreatedAt());
                map().setUpdatedAt(source.getUpdatedAt());
                // Map PresetBouquet entities to bouquet IDs
                using(presetBouquetsToIdsConverter()).map(source.getPresetBouquets(), destination.getBouquets());
            }
        });
    }

    private Converter<List<Long>, List<PresetBouquet>> idxToBouquetsConverter() {
        return context -> {
            List<Long> bouquetIds = context.getSource();
            Preset preset = (Preset) context.getDestination();
            List<PresetBouquet> presetBouquets = new ArrayList<>();

            if (bouquetIds != null) {
                for (int i = 0; i < bouquetIds.size(); i++) {
                    Long id = bouquetIds.get(i);
                    PresetBouquet presetBouquet = new PresetBouquet();
                    Bouquet bouquet = new Bouquet(id); // Assuming you're only using the ID for now
                    presetBouquet.setBouquet(bouquet);
                    presetBouquet.setPreset(preset);
                    presetBouquet.setPositionOrder(i);
                    presetBouquets.add(presetBouquet);

                    // Logging the added bouquet
                    System.out.println("Added PresetBouquet: " + presetBouquet);
                }
            }
            System.out.println("Total PresetBouquets: " + presetBouquets.size());
            return presetBouquets;
        };
    }


    private Converter<List<PresetBouquet>, List<Long>> presetBouquetsToIdsConverter() {
        return context -> {
            List<PresetBouquet> presetBouquets = context.getSource();
            List<Long> bouquetIds = new ArrayList<>();
            if (presetBouquets != null) {
                for (PresetBouquet presetBouquet : presetBouquets) {
                    bouquetIds.add(presetBouquet.getBouquet().getId());
                }
            }
            return bouquetIds;
        };
    }


    public Preset toBouquet(PresetCreateRequest request){
        return mapper.map(request, Preset.class);
    }
    public Preset toBouquet(PresetUpdateRequest request){
        return mapper.map(request, Preset.class);
    }
    public Preset merge(PresetUpdateRequest request, Preset model) {
        Long id = model.getId();
        LocalDateTime createdAt = model.getCreatedAt();
        LocalDateTime updatedAt = LocalDateTime.now();
        model = mapper.map(request, Preset.class);
        model.setId(id);
        model.setCreatedAt(createdAt);
        model.setUpdatedAt(updatedAt);
        return model;
    }

    public PresetCreateResponse toCreateResponse(Preset model){
        return mapper.map(model, PresetCreateResponse.class);
    }

    public PresetUpdateResponse toUpdateResponse(Preset model){
        return mapper.map(model, PresetUpdateResponse.class);
    }

    public PresetDetailResponse toDetailResponse(Preset model){
        return mapper.map(model, PresetDetailResponse.class);
    }

    public PresetCompactResponse toCompactResponse(Preset model){
        return mapper.map(model, PresetCompactResponse.class);
    }

    public Page<PresetCompactResponse> toCompactResponse(Page<Preset> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    public List<PresetCompactResponse> toCompactResponse(List<Preset> list) {
        return list.stream().map(this::toCompactResponse).toList();
    }

    public Collection<PresetCompactResponse> toCompactResponse(Collection<Preset> collection) {
        return collection.stream().map(this::toCompactResponse).collect(Collectors.toList());
    }
}
