package com.wolfott.mangement.device.services;

import com.wolfott.mangement.device.exceptions.ActionNotFound;
import com.wolfott.mangement.device.exceptions.DeviceNotFound;
import com.wolfott.mangement.device.mappers.Enigma2Mapper;
import com.wolfott.mangement.device.models.Enigma2Action;
import com.wolfott.mangement.device.models.Enigma2Device;
import com.wolfott.mangement.device.repositories.Enigma2ActionRepository;
import com.wolfott.mangement.device.repositories.Enigma2DeviceRepository;
import com.wolfott.mangement.device.requests.Enigma2DeviceCreateRequest;
import com.wolfott.mangement.device.requests.Enigma2DeviceUpdateRequest;
import com.wolfott.mangement.device.responses.*;
import com.wolfott.mangement.device.specifications.Enigma2Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Enigma2ServiceImpl implements Enigma2Service {

    @Autowired
    Enigma2DeviceRepository enigma2DeviceRepository;
    @Autowired
    Enigma2ActionRepository enigma2ActionRepository;
    @Autowired
    Enigma2Specification enigma2Specification;
    @Autowired
    Enigma2Mapper enigma2Mapper;

    @Override
    public Enigma2DeviceDetailResponse getOne(Long id) {
        Enigma2Device device = enigma2DeviceRepository.findById(id).orElseThrow(() -> new DeviceNotFound("Device Not Found"));
        return enigma2Mapper.toDetailResponse(device);
    }

    @Override
    public Enigma2ActionDetailResponse getOne(Long deviceId, Long actionId) {
//        throw new RuntimeException("UNDER CONSTRUCTION");
        Enigma2Action action = enigma2ActionRepository.findByIdAndDeviceId(actionId, deviceId).orElseThrow(() -> new ActionNotFound("Action Not Found"));
        return enigma2Mapper.toDetailResponse(action);
    }

    @Override
    public Page<Enigma2DeviceCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<Enigma2Device> spec = enigma2Specification.dynamic(filters);
        Page<Enigma2Device> page = enigma2DeviceRepository.findAll(spec, pageable);
        return enigma2Mapper.toCompactResponse(page);
    }

    @Override
    public Page<Enigma2ActionCompactResponse> getAll(Long deviceId, Map<String, Object> filters, Pageable pageable) {
//        throw new RuntimeException("UNDER CONSTRUCTION");
//        Specification<Enigma2Action> spec = enigma2Specification.dynamic(filters);
        Page<Enigma2Action> page = enigma2ActionRepository.findByDeviceId(deviceId, pageable);
        return enigma2Mapper.toActionCompactResponse(page);
    }

    @Override
    public Enigma2DeviceCreateResponse create(Enigma2DeviceCreateRequest request) {
        Enigma2Device device = enigma2Mapper.toDevice(request);
        device = enigma2DeviceRepository.save(device);
        return enigma2Mapper.toCreateResponse(device);
    }

    @Override
    public Enigma2DeviceUpdateResponse update(Long id, Enigma2DeviceUpdateRequest request) {
        Enigma2Device device = enigma2Mapper.toDevice(request);
        device.setId(id);
        device = enigma2DeviceRepository.save(device);
        return enigma2Mapper.toUpdateResponse(device);
    }

    @Override
    public void delete(Long id) {
        enigma2DeviceRepository.deleteById(id);
    }
}
