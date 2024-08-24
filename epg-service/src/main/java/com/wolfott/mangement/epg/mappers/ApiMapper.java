package com.wolfott.mangement.epg.mappers;

import com.wolfott.mangement.epg.models.EpgApi;
import com.wolfott.mangement.epg.requests.ApiCreateRequest;
import com.wolfott.mangement.epg.requests.ApiUpdateRequest;
import com.wolfott.mangement.epg.responses.ApiCompactResponse;
import com.wolfott.mangement.epg.responses.ApiCreateResponse;
import com.wolfott.mangement.epg.responses.ApiDetailResponse;
import com.wolfott.mangement.epg.responses.ApiUpdateResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class ApiMapper {
    @Autowired
    ModelMapper mapper;
    public EpgApi toApi(ApiCreateRequest request){
        return mapper.map(request, EpgApi.class);
    }
    public EpgApi toApi(ApiUpdateRequest request){
        return mapper.map(request, EpgApi.class);
    }

    public ApiCreateResponse toCreateResponse(EpgApi model){
        return mapper.map(model, ApiCreateResponse.class);
    }

    public ApiUpdateResponse toUpdateResponse(EpgApi model){
        return mapper.map(model, ApiUpdateResponse.class);
    }

    public ApiDetailResponse toDetailResponse(EpgApi model){
        return mapper.map(model, ApiDetailResponse.class);
    }

    public ApiCompactResponse toCompactResponse(EpgApi model){
        return mapper.map(model, ApiCompactResponse.class);
    }

    public Page<ApiCompactResponse> toCompactResponse(Page<EpgApi> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    public Collection<ApiCompactResponse> toCompactResponse(Collection<EpgApi> collection) {
        return collection.stream().map(this::toCompactResponse).collect(Collectors.toList());
    }
}
