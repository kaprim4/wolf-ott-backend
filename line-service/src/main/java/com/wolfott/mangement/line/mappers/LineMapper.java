package com.wolfott.mangement.line.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolfott.mangement.line.models.Line;
import com.wolfott.mangement.line.models.User;
import com.wolfott.mangement.line.requests.LineCreateRequest;
import com.wolfott.mangement.line.requests.LineUpdateRequest;
import com.wolfott.mangement.line.responses.LineCompactResponse;
import com.wolfott.mangement.line.responses.LineCreateResponse;
import com.wolfott.mangement.line.responses.LineDetailResponse;
import com.wolfott.mangement.line.responses.LineUpdateResponse;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
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
//                map().setMemberId(source.getMemberId());
                using(userToUsernameConverter()).map(source.getMember(), destination.getOwner());
                using(userToMemberIdConverter()).map(source.getMember(), destination.getMemberId());
                map().setTrial(source.getIsTrial());
                using(lastActivityArrayToActiveConverter()).map(source.getLastActivityArray(), destination.getActive());
                map().setConnections(source.getMaxConnections());
                using(expDateToDateConverter()).map(source.getExpDate(), destination.getExpiration());
            }
        });

        modelMapper.addMappings(new PropertyMap<Line, LineDetailResponse>() {
            @Override
            protected void configure() {
//                map().setMemberId(source.getMemberId());
                using(userToMemberIdConverter()).map(source.getMember(), destination.getMemberId());
                using(jsonToList()).map(source.getBouquet(), destination.getBouquets());
                using(jsonToList()).map(source.getAllowedOutputs(), destination.getAllowedOutputs());
                using(jsonToList()).map(source.getAllowedIps(), destination.getAllowedIps());
                using(jsonToList()).map(source.getAllowedUa(), destination.getAllowedUa());
                using(jsonToMap()).map(source.getLastActivityArray(), destination.getLastActivityArray());
            }
        });

        modelMapper.addMappings(new PropertyMap<Line, LineUpdateResponse>() {
            @Override
            protected void configure() {
                using(userToMemberIdConverter()).map(source.getMember(), destination.getMemberId());
                using(jsonToList()).map(source.getBouquet(), destination.getBouquets());
                using(jsonToList()).map(source.getAllowedOutputs(), destination.getAllowedOutputs());
                using(jsonToList()).map(source.getAllowedIps(), destination.getAllowedIps());
                using(jsonToList()).map(source.getAllowedUa(), destination.getAllowedUa());
                using(jsonToMap()).map(source.getLastActivityArray(), destination.getLastActivityArray());
            }
        });

        modelMapper.addMappings(new PropertyMap<LineUpdateRequest, Line>() {
            @Override
            protected void configure() {
//                map().setMemberId(source.getMemberId());
                using(memberIdToUserConverter()).map(source.getMemberId(), destination.getMember());
                using(listToJson()).map(source.getBouquets(), destination.getBouquet());
                using(listToJson()).map(source.getAllowedOutputs(), destination.getAllowedOutputs());
                using(listToJson()).map(source.getAllowedIps(), destination.getAllowedIps());
                using(listToJson()).map(source.getAllowedUa(), destination.getAllowedUa());
                using(mapToJson()).map(source.getLastActivityArray(), destination.getLastActivityArray());
            }
        });

        modelMapper.addMappings(new PropertyMap<Line, LineCreateResponse>() {
            @Override
            protected void configure() {
                using(userToMemberIdConverter()).map(source.getMember(), destination.getMemberId());
                using(jsonToList()).map(source.getBouquet(), destination.getBouquets());
                using(jsonToList()).map(source.getAllowedOutputs(), destination.getAllowedOutputs());
                using(jsonToList()).map(source.getAllowedIps(), destination.getAllowedIps());
                using(jsonToList()).map(source.getAllowedUa(), destination.getAllowedUa());
                using(jsonToMap()).map(source.getLastActivityArray(), destination.getLastActivityArray());
            }
        });

        modelMapper.addMappings(new PropertyMap<LineCreateRequest, Line>() {
            @Override
            protected void configure() {
                using(memberIdToUserConverter()).map(source.getMemberId(), destination.getMember());
                using(listToJson()).map(source.getBouquets(), destination.getBouquet());
                using(listToJson()).map(source.getAllowedOutputs(), destination.getAllowedOutputs());
                using(listToJson()).map(source.getAllowedIps(), destination.getAllowedIps());
                using(listToJson()).map(source.getAllowedUa(), destination.getAllowedUa());
                using(mapToJson()).map(source.getLastActivityArray(), destination.getLastActivityArray());
                using(booleanToInteger()).map(source.getAdminEnabled(), destination.getAdminEnabled());
            }
        });
    }

    private Converter<Boolean, Integer> booleanToInteger() {
        return context -> Optional.ofNullable(context.getSource()).map(value -> value ? 1 : 0).orElse(null);
    }

    private Converter<String, List> jsonToList() {
        return context -> {
            String json = context.getSource();
            if (json == null) return Collections.emptyList();
            try {
                return new ObjectMapper().readValue(json, new TypeReference<List>() {
                });
            } catch (JsonProcessingException e) {
                log.error("Error parsing JSON to list: {}", json, e);
                return Collections.emptyList();
            }
        };
    }

    private Converter<List, String> listToJson() {
        return context -> {
            List<?> list = context.getSource();
            if (list == null) return null; // Return null for a null input
            try {
                return new ObjectMapper().writeValueAsString(list);
            } catch (JsonProcessingException e) {
                log.error("Error converting list to JSON: {}", list, e);
                return null; // Or handle it as needed, e.g., return an empty string or a specific error message
            }
        };
    }


    private Converter<String, Map<String, Object>> jsonToMap() {
        return context -> {
            String json = context.getSource();
            if (json == null) return Collections.emptyMap();
            try {
                return new ObjectMapper().readValue(json, new TypeReference<Map<String, Object>>() {
                });
            } catch (JsonProcessingException e) {
                log.error("Error parsing JSON to map: {}", json, e);
                return Collections.emptyMap();
            }
        };
    }

    private Converter<Map<String, Object>, String> mapToJson() {
        return context -> {
            Map<String, Object> map = context.getSource();
            if (map == null) return null; // Return null for a null input
            try {
                return new ObjectMapper().writeValueAsString(map);
            } catch (JsonProcessingException e) {
                log.error("Error converting map to JSON: {}", map, e);
                return null; // Or handle it as needed, e.g., return an empty string or a specific error message
            }
        };
    }

    private Converter<User, String> userToUsernameConverter() {
        return context -> Optional.ofNullable(context.getSource())
                .map(User::getUsername)
                .orElse(null); // "Anonymous"
    }

    private Converter<User, Long> userToMemberIdConverter() {
        return context -> Optional.ofNullable(context.getSource())
                .map(User::getId)
                .orElse(null); // "Anonymous"
    }

    private Converter<Long, User> memberIdToUserConverter() {
        return context -> Optional.ofNullable(context.getSource())
                .map(User::new)
                .map(user -> {
                    log.info("User initiated: {}", user);
                    return user;
                })
                .orElseGet(() -> {
                    log.info("User initiation failed: source is null");
                    return null;
                });
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