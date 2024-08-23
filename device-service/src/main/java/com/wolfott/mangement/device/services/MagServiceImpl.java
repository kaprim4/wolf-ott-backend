package com.wolfott.mangement.device.services;

import com.wolfott.mangement.device.exceptions.ClaimNotFound;
import com.wolfott.mangement.device.exceptions.DeviceNotFound;
import com.wolfott.mangement.device.exceptions.EventNotFound;
import com.wolfott.mangement.device.mappers.MagMapper;
import com.wolfott.mangement.device.models.MagClaim;
import com.wolfott.mangement.device.models.MagDevice;
import com.wolfott.mangement.device.models.MagEvent;
import com.wolfott.mangement.device.repositories.MagClaimRepository;
import com.wolfott.mangement.device.repositories.MagDeviceRepository;
import com.wolfott.mangement.device.repositories.MagEventRepository;
import com.wolfott.mangement.device.requests.MagDeviceCreateRequest;
import com.wolfott.mangement.device.requests.MagDeviceUpdateRequest;
import com.wolfott.mangement.device.responses.*;
import com.wolfott.mangement.device.specifications.MagSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MagServiceImpl implements MagService {

    @Autowired
    MagDeviceRepository magDeviceRepository;
    @Autowired
    MagClaimRepository magClaimRepository;
    @Autowired
    MagEventRepository magEventRepository;
    @Autowired
    MagSpecification magSpecification;
    @Autowired
    MagMapper magMapper;

    @Override
    public MagDeviceDetailResponse getOne(Long id) {
        MagDevice device = magDeviceRepository.findById(id).orElseThrow(() -> new DeviceNotFound("Device Not Found"));
        return magMapper.toDetailResponse(device);
    }

    @Override
    public MagClaimDetailResponse getClaim(Long deviceId, String claimId) {
//        throw new RuntimeException("UNDER CONSTRUCTION");
        MagClaim claim = magClaimRepository.findByIdAndMagId(claimId, deviceId).orElseThrow(() -> new ClaimNotFound("Claim Not Found"));
        return magMapper.toDetailResponse(claim);
    }

    @Override
    public MagEventDetailResponse getEvent(Long deviceId, Long eventId) {
//        throw new RuntimeException("UNDER CONSTRUCTION");
        MagEvent event = magEventRepository.findByIdAndMagDeviceId(eventId, deviceId).orElseThrow(() -> new EventNotFound("Event Not Found"));
        return magMapper.toDetailResponse(event);
    }

    @Override
    public Page<MagDeviceCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<MagDevice> spec = magSpecification.dynamic(filters);
        Page<MagDevice> page = magDeviceRepository.findAll(spec, pageable);
        return magMapper.toCompactResponse(page);
    }

    @Override
    public Page<MagClaimCompactResponse> getClaims(Long deviceId, Map<String, Object> filters, Pageable pageable) {
//        throw new RuntimeException("UNDER CONSTRUCTION");
        Page<MagClaim> page = magClaimRepository.findByMagId(deviceId, pageable);
        return magMapper.toClaimCompactResponse(page);
    }

    @Override
    public Page<MagEventCompactResponse> getEvents(Long deviceId, Map<String, Object> filters, Pageable pageable) {
//        throw new RuntimeException("UNDER CONSTRUCTION");
        Page<MagEvent> page = magEventRepository.findByMagDeviceId(deviceId, pageable);
        return magMapper.toEventCompactResponse(page);
    }

    @Override
    public MagDeviceCreateResponse create(MagDeviceCreateRequest request) {
        MagDevice device = magMapper.toDevice(request);
        device = magDeviceRepository.save(device);
        return magMapper.toCreateResponse(device);
    }

    @Override
    public MagDeviceUpdateResponse update(Long id, MagDeviceUpdateRequest request) {
        boolean exist = magDeviceRepository.existsById(id);
        if (!exist)
            throw new DeviceNotFound("Unable to update this device");
        MagDevice device = magMapper.toDevice(request);
        device.setId(id);
        device = magDeviceRepository.save(device);
        return magMapper.toUpdateResponse(device);
    }

    @Override
    public void delete(Long id) {
        magDeviceRepository.deleteById(id);
    }
}
