package com.wolfott.stream_mangement.mappers;

import com.wolfott.stream_mangement.models.Stream;
import com.wolfott.stream_mangement.responses.StreamCompactResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StreamMapper {
    @Autowired
    ModelMapper mapper;

    public StreamCompactResponse toCompactResponse(Stream model) {
        return mapper.map(model, StreamCompactResponse.class);
    }

    public Page<StreamCompactResponse> toCompactResponse(Page<Stream> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    public Collection<StreamCompactResponse> toCompactResponse(Collection<Stream> collection) {
        return collection.stream().map(this::toCompactResponse).collect(Collectors.toList());
    }

    public List<StreamCompactResponse> toCompactResponse(List<Stream> collection) {
        return collection.stream().map(this::toCompactResponse).collect(Collectors.toList());
    }
}
