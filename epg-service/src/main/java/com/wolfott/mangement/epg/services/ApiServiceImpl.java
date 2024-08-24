package com.wolfott.mangement.epg.services;

import com.wolfott.mangement.epg.exceptions.ApiNotFoundException;
import com.wolfott.mangement.epg.mappers.ApiMapper;
import com.wolfott.mangement.epg.models.EpgApi;
import com.wolfott.mangement.epg.repositories.ApiRepository;
import com.wolfott.mangement.epg.requests.ApiCreateRequest;
import com.wolfott.mangement.epg.requests.ApiUpdateRequest;
import com.wolfott.mangement.epg.responses.ApiCompactResponse;
import com.wolfott.mangement.epg.responses.ApiCreateResponse;
import com.wolfott.mangement.epg.responses.ApiDetailResponse;
import com.wolfott.mangement.epg.responses.ApiUpdateResponse;
import com.wolfott.mangement.epg.specifications.ApiSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    ApiRepository apiRepository;
    @Autowired
    ApiSpecification apiSpecification;
    @Autowired
    ApiMapper apiMapper;

    @Override
    public ApiDetailResponse getOne(Long id) {
        EpgApi api = apiRepository.findById(id).orElseThrow(() -> new ApiNotFoundException("Api Not Found"));
        return apiMapper.toDetailResponse(api);
    }

    @Override
    public Page<ApiCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<EpgApi> spec = apiSpecification.dynamic(filters);
        Page<EpgApi> page = apiRepository.findAll(spec, pageable);
        return apiMapper.toCompactResponse(page);
    }

    @Override
    public ApiCreateResponse create(ApiCreateRequest request) {
        EpgApi api = apiMapper.toApi(request);
        api = apiRepository.save(api);
        return apiMapper.toCreateResponse(api);
    }

    @Override
    public ApiUpdateResponse update(Long id, ApiUpdateRequest request) {
        EpgApi api = apiMapper.toApi(request);
        api.setStationId(id);
        api = apiRepository.save(api);
        return apiMapper.toUpdateResponse(api);
    }

    @Override
    public void delete(Long id) {
        apiRepository.deleteById(id);
    }
}
