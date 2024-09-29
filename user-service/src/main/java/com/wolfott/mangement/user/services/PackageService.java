package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.requests.PackageCreateRequest;
import com.wolfott.mangement.user.requests.PackageUpdateRequest;
import com.wolfott.mangement.user.responses.PackageCompactResponse;
import com.wolfott.mangement.user.responses.PackageCreateResponse;
import com.wolfott.mangement.user.responses.PackageDetailResponse;
import com.wolfott.mangement.user.responses.PackageUpdateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PackageService {
    PackageDetailResponse getOne(String id);
    List<PackageCompactResponse> getAll(Map<String, Object> filters);
    Page<PackageCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);
    PackageCreateResponse create(PackageCreateRequest request);
    PackageUpdateResponse update(String id, PackageUpdateRequest request);
    void delete(String id);
}
