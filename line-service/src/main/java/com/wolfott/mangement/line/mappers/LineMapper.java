package com.wolfott.mangement.line.mappers;

import com.wolfott.mangement.line.models.Line;
import com.wolfott.mangement.line.models.User;
import com.wolfott.mangement.line.requests.LineCreateRequest;
import com.wolfott.mangement.line.requests.LineUpdateRequest;
import com.wolfott.mangement.line.responses.LineCompactResponse;
import com.wolfott.mangement.line.responses.LineCreateResponse;
import com.wolfott.mangement.line.responses.LineDetailResponse;
import com.wolfott.mangement.line.responses.LineUpdateResponse;
import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LineMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public LineMapper(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void setupMappings() {
        modelMapper.addMappings(new PropertyMap<Line, LineCompactResponse>() {
            @Override
            protected void configure() {
                map().setMemberId(source.getMemberId());
                using(userToStringConverter()).map(source.getMember(), destination.getOwner());
                map().setTrial(source.getIsTrial());
                using(lastActivityArrayToActiveConverter()).map(source.getLastActivityArray(), destination.getActive());
                map().setConnections(source.getMaxConnections());
                using(expDateToDateConverter()).map(source.getExpDate(), destination.getExpiration());
            }
        });
    }

    private Converter<User, String> userToStringConverter() {
        return context -> Optional.ofNullable(context.getSource())
                .map(User::getUsername)
                .orElse("Anonymous");
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

    // Convert Line entity to LineCompactResponse
    public LineCompactResponse toLineCompactResponse(Line line) {
        return modelMapper.map(line, LineCompactResponse.class);
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
        Long id = existingLine.getId();
        Line updatedLine = modelMapper.map(existingLine, Line.class);
        modelMapper.map(request, updatedLine);
        updatedLine.setId(id);
        return updatedLine;
    }
    // Convert Page<Line> to Page<LineCompactResponse>
    public Page<LineCompactResponse> toLineCompactResponsePage(Page<Line> linePage) {
        return new PageImpl<>(
                linePage.getContent().stream()
                        .map(this::toLineCompactResponse)
                        .collect(Collectors.toList()),
                linePage.getPageable(),
                linePage.getTotalElements()
        );
    }

    public List<LineCompactResponse> toLineCompactResponsePage(List<Line> list) {
        return list.stream().map(this::toLineCompactResponse).toList();
    }
}