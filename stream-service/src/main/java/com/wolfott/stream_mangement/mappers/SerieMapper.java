package com.wolfott.stream_mangement.mappers;

import com.wolfott.stream_mangement.models.StreamSeries;
import com.wolfott.stream_mangement.responses.SerieCompactResponse;
import com.wolfott.stream_mangement.responses.SerieDetailResponse;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SerieMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public SerieMapper(ModelMapper modelMapper) {
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

    // Convert Serie entity to CategoryCompactResponse
    public SerieCompactResponse toSerieCompactResponse(StreamSeries serie) {
        return modelMapper.map(serie, SerieCompactResponse.class);
    }
    // Convert Serie entity to SerieDetailResponse
    public SerieDetailResponse toSerieDetailResponse(StreamSeries serie) {
        return modelMapper.map(serie, SerieDetailResponse.class);
    }

    // Convert Page<Serie> to Page<SerieCompactResponse>
    public Page<SerieCompactResponse> toSerieCompactResponsePage(Page<StreamSeries> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toSerieCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    public List<SerieCompactResponse> toSerieCompactResponseList(List<StreamSeries> list) {
        return list.stream().map(this::toSerieCompactResponse).toList();
    }
}
