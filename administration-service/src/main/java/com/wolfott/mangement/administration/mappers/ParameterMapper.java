package com.wolfott.mangement.administration.mappers;

import com.wolfott.mangement.administration.models.Parameter;
import com.wolfott.mangement.administration.requests.ParameterCreateRequest;
import com.wolfott.mangement.administration.requests.ParameterUpdateRequest;
import com.wolfott.mangement.administration.responses.ParameterCompactResponse;
import com.wolfott.mangement.administration.responses.ParameterCreateResponse;
import com.wolfott.mangement.administration.responses.ParameterDetailResponse;
import com.wolfott.mangement.administration.responses.ParameterUpdateResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParameterMapper {
    @Autowired
    ModelMapper mapper;
    public Parameter toParameter(ParameterCreateRequest request){
        return mapper.map(request, Parameter.class);
    }
    public Parameter toParameter(ParameterUpdateRequest request){
        return mapper.map(request, Parameter.class);
    }

    public ParameterCreateResponse toCreateResponse(Parameter model){
        return mapper.map(model, ParameterCreateResponse.class);
    }

    public ParameterUpdateResponse toUpdateResponse(Parameter model){
        return mapper.map(model, ParameterUpdateResponse.class);
    }

    public ParameterDetailResponse toDetailResponse(Parameter model){
        return mapper.map(model, ParameterDetailResponse.class);
    }

    public ParameterCompactResponse toCompactResponse(Parameter model){
        return mapper.map(model, ParameterCompactResponse.class);
    }

    public Page<ParameterCompactResponse> toCompactResponse(Page<Parameter> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    public List<ParameterCompactResponse> toCompactResponse(List<Parameter> list) {
        return list.stream().map(this::toCompactResponse).toList();
    }

    public Collection<ParameterCompactResponse> toCompactResponse(Collection<Parameter> collection) {
        return collection.stream().map(this::toCompactResponse).collect(Collectors.toList());
    }
}
