package com.wolfott.mangement.device.controllers;

import com.wolfott.mangement.device.requests.MagDeviceCreateRequest;
import com.wolfott.mangement.device.requests.MagDeviceUpdateRequest;
import com.wolfott.mangement.device.responses.*;
import com.wolfott.mangement.device.services.MagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/devices/mag")
public class MagController {

    @Autowired
    MagService magService;

    @GetMapping("/{id}")
    public MagDeviceDetailResponse getOne(@PathVariable("id") Long id){
        return magService.getOne(id);
    }
    @GetMapping("/{device_id}/claims/{claim_id}")
    public MagClaimDetailResponse getClaim(@PathVariable("device_id") Long deviceId, @PathVariable("claim_id") String claimId){
        return magService.getClaim(deviceId, claimId);
    }
    @GetMapping("/{device_id}/events/{event_id}")
    public MagEventDetailResponse getEvent(@PathVariable("device_id") Long deviceId, @PathVariable("event_id") Long eventId){
        return magService.getEvent(deviceId, eventId);
    }

    @GetMapping
    public Page<MagDeviceCompactResponse> getAll(@RequestParam Map<String, Object> filters, Pageable pageable){
        return magService.getAll(filters, pageable);
    }
    @GetMapping("/{id}/claims")
    public Page<MagClaimCompactResponse> getClaims(@PathVariable("id") Long id, @RequestParam Map<String, Object> filters, Pageable pageable){
        return magService.getClaims(id, filters, pageable);
    }
    @GetMapping("/{id}/events")
    public Page<MagEventCompactResponse> getEvents(@PathVariable("id") Long id, @RequestParam Map<String, Object> filters, Pageable pageable){
        return magService.getEvents(id, filters, pageable);
    }

    @PostMapping
    public MagDeviceCreateResponse create(@RequestBody MagDeviceCreateRequest request){
        return magService.create(request);
    }

    @PutMapping("/{id}")
    public MagDeviceUpdateResponse update(@PathVariable("id") Long id, @RequestBody MagDeviceUpdateRequest request){
        return magService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        magService.delete(id);
    }
}
