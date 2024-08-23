package com.wolfott.mangement.epg.mappers;

import com.wolfott.mangement.epg.models.Epg;
import com.wolfott.mangement.epg.requests.EpgCreateRequest;
import com.wolfott.mangement.epg.requests.EpgUpdateRequest;
import com.wolfott.mangement.epg.responses.EpgCompactResponse;
import com.wolfott.mangement.epg.responses.EpgCreateResponse;
import com.wolfott.mangement.epg.responses.EpgDetailResponse;
import com.wolfott.mangement.epg.responses.EpgUpdateResponse;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class EpgMapper {
    @Autowired
    ModelMapper mapper;
    public Epg toEpg(EpgCreateRequest request){
        return mapper.map(request, Epg.class);
    }
    public Epg toEpg(EpgUpdateRequest request){
        return mapper.map(request, Epg.class);
    }

    public EpgCreateResponse toCreateResponse(Epg model){
        return mapper.map(model, EpgCreateResponse.class);
    }

    public EpgUpdateResponse toUpdateResponse(Epg model){
        return mapper.map(model, EpgUpdateResponse.class);
    }

    public EpgDetailResponse toDetailResponse(Epg model){
        return mapper.map(model, EpgDetailResponse.class);
    }

    public EpgCompactResponse toCompactResponse(Epg model){
        return mapper.map(model, EpgCompactResponse.class);
    }

    public Page<EpgCompactResponse> toCompactResponse(Page<Epg> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    public Collection<EpgCompactResponse> toCompactResponse(Collection<Epg> collection) {
        return collection.stream().map(this::toCompactResponse).collect(Collectors.toList());
    }
}
