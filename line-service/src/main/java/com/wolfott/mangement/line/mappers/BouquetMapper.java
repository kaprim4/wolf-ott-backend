package com.wolfott.mangement.line.mappers;

import com.wolfott.mangement.line.models.Bouquet;
import com.wolfott.mangement.line.requests.BouquetCreateRequest;
import com.wolfott.mangement.line.requests.BouquetUpdateRequest;
import com.wolfott.mangement.line.responses.BouquetCompactResponse;
import com.wolfott.mangement.line.responses.BouquetCreateResponse;
import com.wolfott.mangement.line.responses.BouquetDetailResponse;
import com.wolfott.mangement.line.responses.BouquetUpdateResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class BouquetMapper {
    @Autowired
    ModelMapper mapper;
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
}
