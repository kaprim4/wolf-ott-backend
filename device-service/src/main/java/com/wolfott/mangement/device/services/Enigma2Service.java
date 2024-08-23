package com.wolfott.mangement.device.services;

import com.wolfott.mangement.device.requests.Enigma2DeviceCreateRequest;
import com.wolfott.mangement.device.requests.Enigma2DeviceUpdateRequest;
import com.wolfott.mangement.device.responses.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface Enigma2Service {
    Enigma2DeviceDetailResponse getOne(Long id);
    Enigma2ActionDetailResponse getOne(Long deviceId, String actionId);
    Page<Enigma2DeviceCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);
    Page<Enigma2ActionCompactResponse> getAll(Long deviceId, Map<String, Object> filters, Pageable pageable);
    Enigma2DeviceCreateResponse create(Enigma2DeviceCreateRequest request);
    Enigma2DeviceUpdateResponse update(Long id, Enigma2DeviceUpdateRequest request);
    void delete(Long id);
}
