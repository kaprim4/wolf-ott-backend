package com.wolfott.mangement.administration.services;

import com.wolfott.mangement.administration.requests.ParameterCreateRequest;
import com.wolfott.mangement.administration.requests.ParameterUpdateRequest;
import com.wolfott.mangement.administration.responses.ParameterCompactResponse;
import com.wolfott.mangement.administration.responses.ParameterCreateResponse;
import com.wolfott.mangement.administration.responses.ParameterDetailResponse;
import com.wolfott.mangement.administration.responses.ParameterUpdateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ParameterService {
    List<ParameterCompactResponse> getAll();
    Page<ParameterCompactResponse> getAll(Pageable pageable);
    ParameterDetailResponse getOne(Long id);
    ParameterDetailResponse getOneByKey(String key);
    ParameterCreateResponse create(ParameterCreateRequest request);
    ParameterUpdateResponse update(Long id, ParameterUpdateRequest request);
    void delete(Long id);

}
