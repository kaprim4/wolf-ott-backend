package com.wolfott.mangement.epg.mappers;

import com.wolfott.mangement.epg.models.EpgLanguage;
import com.wolfott.mangement.epg.requests.LanguageCreateRequest;
import com.wolfott.mangement.epg.requests.LanguageUpdateRequest;
import com.wolfott.mangement.epg.responses.LanguageCompactResponse;
import com.wolfott.mangement.epg.responses.LanguageCreateResponse;
import com.wolfott.mangement.epg.responses.LanguageDetailResponse;
import com.wolfott.mangement.epg.responses.LanguageUpdateResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class LanguageMapper {
    @Autowired
    ModelMapper mapper;
    public EpgLanguage toLanguage(LanguageCreateRequest request){
        return mapper.map(request, EpgLanguage.class);
    }
    public EpgLanguage toLanguage(LanguageUpdateRequest request){
        return mapper.map(request, EpgLanguage.class);
    }

    public LanguageCreateResponse toCreateResponse(EpgLanguage model){
        return mapper.map(model, LanguageCreateResponse.class);
    }

    public LanguageUpdateResponse toUpdateResponse(EpgLanguage model){
        return mapper.map(model, LanguageUpdateResponse.class);
    }

    public LanguageDetailResponse toDetailResponse(EpgLanguage model){
        return mapper.map(model, LanguageDetailResponse.class);
    }

    public LanguageCompactResponse toCompactResponse(EpgLanguage model){
        return mapper.map(model, LanguageCompactResponse.class);
    }

    public Page<LanguageCompactResponse> toCompactResponse(Page<EpgLanguage> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    public Collection<LanguageCompactResponse> toCompactResponse(Collection<EpgLanguage> collection) {
        return collection.stream().map(this::toCompactResponse).collect(Collectors.toList());
    }
}
