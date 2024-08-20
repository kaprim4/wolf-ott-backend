package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.requests.ProfileCreateRequest;
import com.wolfott.mangement.user.requests.ProfileUpdateRequest;
import com.wolfott.mangement.user.responses.ProfileCompactResponse;
import com.wolfott.mangement.user.responses.ProfileCreateResponse;
import com.wolfott.mangement.user.responses.ProfileDetailResponse;
import com.wolfott.mangement.user.responses.ProfileUpdateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ProfileService {
    ProfileDetailResponse getOne(Long id);
    Page<ProfileCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);
    ProfileCreateResponse create(ProfileCreateRequest request);
    ProfileUpdateResponse update(Long id, ProfileUpdateRequest request);
    void delete(Long id);
}
