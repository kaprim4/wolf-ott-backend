package com.wolfott.mangement.device.services;

import com.wolfott.mangement.device.requests.MagDeviceCreateRequest;
import com.wolfott.mangement.device.requests.MagDeviceUpdateRequest;
import com.wolfott.mangement.device.responses.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface MagService {
    MagDeviceDetailResponse getOne(Long id);
    MagClaimDetailResponse getClaim(Long deviceId, String claimId);
    MagEventDetailResponse getEvent(Long deviceId, Long eventId);
    Page<MagDeviceCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);
    Page<MagClaimCompactResponse> getClaims(Long deviceId, Map<String, Object> filters, Pageable pageable);
    Page<MagEventCompactResponse> getEvents(Long deviceId, Map<String, Object> filters, Pageable pageable);
    MagDeviceCreateResponse create(MagDeviceCreateRequest request);
    MagDeviceUpdateResponse update(Long id, MagDeviceUpdateRequest request);
    void delete(Long id);
}
