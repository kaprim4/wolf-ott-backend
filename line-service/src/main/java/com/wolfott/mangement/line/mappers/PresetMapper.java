package com.wolfott.mangement.line.mappers;

import com.wolfott.mangement.line.models.Preset;
import com.wolfott.mangement.line.requests.PresetCreateRequest;
import com.wolfott.mangement.line.requests.PresetUpdateRequest;
import com.wolfott.mangement.line.responses.PresetCompactResponse;
import com.wolfott.mangement.line.responses.PresetCreateResponse;
import com.wolfott.mangement.line.responses.PresetDetailResponse;
import com.wolfott.mangement.line.responses.PresetUpdateResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class PresetMapper {
    @Autowired
    ModelMapper mapper;
    public Preset toBouquet(PresetCreateRequest request){
        return mapper.map(request, Preset.class);
    }
    public Preset toBouquet(PresetUpdateRequest request){
        return mapper.map(request, Preset.class);
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

    public Collection<PresetCompactResponse> toCompactResponse(Collection<Preset> collection) {
        return collection.stream().map(this::toCompactResponse).collect(Collectors.toList());
    }
}
