package com.wolfott.mangement.device.controllers;

import com.wolfott.mangement.device.requests.Enigma2DeviceCreateRequest;
import com.wolfott.mangement.device.requests.Enigma2DeviceUpdateRequest;
import com.wolfott.mangement.device.responses.*;
import com.wolfott.mangement.device.services.Enigma2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/devices/enigma2")
public class Enigma2Controller {

    @Autowired
    Enigma2Service enigma2Service;

    @GetMapping("/{id}")
    public Enigma2DeviceDetailResponse getOne(@PathVariable("id") Long id){
        return enigma2Service.getOne(id);
    }

    @GetMapping("/{device_id}/actions/{action_id}")
    public Enigma2ActionDetailResponse getAction(@PathVariable("device_id") Long deviceId, @PathVariable("action_id") Long actionId){
        return enigma2Service.getOne(deviceId, actionId);
    }

    @GetMapping
    public Page<Enigma2DeviceCompactResponse> getAll(@RequestParam Map<String, Object> filters, Pageable pageable){
        return enigma2Service.getAll(filters, pageable);
    }

    @GetMapping("/{id}/actions")
    public Page<Enigma2ActionCompactResponse> getActions(@PathVariable("id") Long deviceId, @RequestParam Map<String, Object> filters, Pageable pageable){
        return enigma2Service.getAll(deviceId, filters, pageable);
    }

    @PostMapping
    public Enigma2DeviceCreateResponse create(@RequestBody Enigma2DeviceCreateRequest request){
        return enigma2Service.create(request);
    }

    @PutMapping("/{id}")
    public Enigma2DeviceUpdateResponse update(@PathVariable("id") Long id, @RequestBody Enigma2DeviceUpdateRequest request){
        return enigma2Service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        enigma2Service.delete(id);
    }

}
