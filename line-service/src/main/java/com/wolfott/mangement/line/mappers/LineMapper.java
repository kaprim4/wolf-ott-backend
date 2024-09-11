package com.wolfott.mangement.line.mappers;

import com.wolfott.mangement.line.models.Line;
import com.wolfott.mangement.line.models.LineListDto;
import com.wolfott.mangement.line.requests.LineCreateRequest;
import com.wolfott.mangement.line.requests.LineUpdateRequest;
import com.wolfott.mangement.line.responses.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class LineMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public LineMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Convert Line entity to LineCompactResponse
    public LineCompactResponse toLineCompactResponse(Line line) {
        return modelMapper.map(line, LineCompactResponse.class);
    }

    // Convert Line entity to LineCompactResponse
    public LineListDto toLineCompactListResponse(Line line) {
        return modelMapper.map(line, LineListDto.class);
    }

    // Convert Line entity to LineDetailResponse
    public LineDetailResponse toLineDetailResponse(Line line) {
        return modelMapper.map(line, LineDetailResponse.class);
    }

    // Convert Line entity to LineCreateResponse
    public LineCreateResponse toLineCreateResponse(Line line) {
        return modelMapper.map(line, LineCreateResponse.class);
    }

    // Convert Line entity to LineUpdateResponse
    public LineUpdateResponse toLineUpdateResponse(Line line) {
        return modelMapper.map(line, LineUpdateResponse.class);
    }

    // Convert LineCreateRequest to Line entity
    public Line toLine(LineCreateRequest request) {
        return modelMapper.map(request, Line.class);
    }

    // Convert LineUpdateRequest to Line entity and merge with existing entity
    public Line mergeLine(LineUpdateRequest request, Line existingLine) {
        // Create a copy of the existing line and update its properties
        Line updatedLine = modelMapper.map(existingLine, Line.class);
        modelMapper.map(request, updatedLine);
        return updatedLine;
    }

    public Page<LineCompactResponse> toLineCompactResponsePage(Page<Line> linePage) {
        return new PageImpl<>(
                linePage.getContent().stream()
                        .map(this::toLineCompactResponse)
                        .collect(Collectors.toList()),
                linePage.getPageable(),
                linePage.getTotalElements()
        );
    }
}
