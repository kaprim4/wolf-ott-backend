package com.wolfott.mangement.administration.services;

import com.wolfott.mangement.administration.exceptions.ParameterNotFoundException;
import com.wolfott.mangement.administration.mappers.ParameterMapper;
import com.wolfott.mangement.administration.models.Parameter;
import com.wolfott.mangement.administration.repositories.ParameterRepository;
import com.wolfott.mangement.administration.requests.ParameterCreateRequest;
import com.wolfott.mangement.administration.requests.ParameterUpdateRequest;
import com.wolfott.mangement.administration.responses.ParameterCompactResponse;
import com.wolfott.mangement.administration.responses.ParameterCreateResponse;
import com.wolfott.mangement.administration.responses.ParameterDetailResponse;
import com.wolfott.mangement.administration.responses.ParameterUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterServiceImpl implements ParameterService {
    @Autowired
    private ParameterRepository parameterRepository;
    @Autowired
    private ParameterMapper parameterMapper;
    @Override
    public List<ParameterCompactResponse> getAll() {
        return parameterRepository.findAll().stream().map(parameterMapper::toCompactResponse).toList();
    }

    @Override
    public Page<ParameterCompactResponse> getAll(Pageable pageable) {
        return parameterRepository.findAll(pageable).map(parameterMapper::toCompactResponse);
    }

    @Override
    public ParameterDetailResponse getOne(Long id) {
        return parameterRepository.findById(id).map(parameterMapper::toDetailResponse).orElseThrow(ParameterNotFoundException::new);
    }

    @Override
    public ParameterDetailResponse getOneByKey(String key) {
        return parameterRepository.findFirstByKey(key).map(parameterMapper::toDetailResponse).orElseThrow(ParameterNotFoundException::new);
    }

    @Override
    public ParameterCreateResponse create(ParameterCreateRequest request) {
        Parameter param = parameterMapper.toParameter(request);
        param = parameterRepository.save(param);
        return parameterMapper.toCreateResponse(param);
    }

    @Override
    public ParameterUpdateResponse update(Long id, ParameterUpdateRequest request) {
        Parameter param = parameterMapper.toParameter(request);
        param.setId(id);
        param = parameterRepository.save(param);
        return parameterMapper.toUpdateResponse(param);
    }

    @Override
    public void delete(Long id) {
        parameterRepository.deleteById(id);
    }
}
