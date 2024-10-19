package com.wolfott.stream_mangement.mappers;

import com.wolfott.stream_mangement.models.Stream;
import com.wolfott.stream_mangement.responses.StreamCompactResponse;
import com.wolfott.stream_mangement.responses.StreamDetailResponse;
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
public class StreamMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public StreamMapper(ModelMapper modelMapper) {
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

    // Convert Category entity to CategoryCompactResponse
    public StreamCompactResponse toStreamCompactResponse(Stream stream) {
        return modelMapper.map(stream, StreamCompactResponse.class);
    }
    // Convert Category entity to StreamDetailResponse
    public StreamDetailResponse toStreamDetailResponse(Stream category) {
        return modelMapper.map(category, StreamDetailResponse.class);
    }

    // Convert Page<Category> to Page<StreamCompactResponse>
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
