package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.requests.GroupCreateRequest;
import com.wolfott.mangement.user.requests.GroupUpdateRequest;
import com.wolfott.mangement.user.responses.GroupCompactResponse;
import com.wolfott.mangement.user.responses.GroupCreateResponse;
import com.wolfott.mangement.user.responses.GroupDetailResponse;
import com.wolfott.mangement.user.responses.GroupUpdateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface GroupService {
    GroupDetailResponse getOne(Long id);
    List<GroupCompactResponse> getAll(Map<String, Object> filters);
    Page<GroupCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);
    GroupCreateResponse create(GroupCreateRequest request);
    GroupUpdateResponse update(Long id, GroupUpdateRequest request);
    void delete(Long id);
}
