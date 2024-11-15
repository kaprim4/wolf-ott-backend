package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.exceptions.ParameterNotFoundException;
import com.wolfott.mangement.user.mappers.ParameterMapper;
import com.wolfott.mangement.user.models.Parameter;
import com.wolfott.mangement.user.repositories.ParameterRepository;
import com.wolfott.mangement.user.requests.ParameterCreateRequest;
import com.wolfott.mangement.user.requests.ParameterUpdateRequest;
import com.wolfott.mangement.user.responses.ParameterCompactResponse;
import com.wolfott.mangement.user.responses.ParameterCreateResponse;
import com.wolfott.mangement.user.responses.ParameterDetailResponse;
import com.wolfott.mangement.user.responses.ParameterUpdateResponse;
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
