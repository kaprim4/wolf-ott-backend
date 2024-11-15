package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.models.Parameter;
import com.wolfott.mangement.user.requests.ParameterCreateRequest;
import com.wolfott.mangement.user.requests.ParameterUpdateRequest;
import com.wolfott.mangement.user.responses.ParameterCompactResponse;
import com.wolfott.mangement.user.responses.ParameterCreateResponse;
import com.wolfott.mangement.user.responses.ParameterDetailResponse;
import com.wolfott.mangement.user.responses.ParameterUpdateResponse;
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
