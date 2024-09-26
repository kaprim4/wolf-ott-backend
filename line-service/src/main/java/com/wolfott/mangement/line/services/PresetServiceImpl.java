package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.exceptions.PresetNotFoundException;
import com.wolfott.mangement.line.mappers.PresetMapper;
import com.wolfott.mangement.line.models.Preset;
import com.wolfott.mangement.line.repositories.PresetRepository;
import com.wolfott.mangement.line.requests.PresetCreateRequest;
import com.wolfott.mangement.line.requests.PresetUpdateRequest;
import com.wolfott.mangement.line.responses.PresetCompactResponse;
import com.wolfott.mangement.line.responses.PresetCreateResponse;
import com.wolfott.mangement.line.responses.PresetDetailResponse;
import com.wolfott.mangement.line.responses.PresetUpdateResponse;
import com.wolfott.mangement.line.specifications.PresetSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PresetServiceImpl implements PresetService {
    @Autowired
    PresetRepository presetRepository;
    @Autowired
    PresetSpecification presetSpecification;
    @Autowired
    PresetMapper presetMapper;

    @Override
    public PresetDetailResponse getOne(Long id) {
        Preset preset = presetRepository.findById(id).orElseThrow(() -> new PresetNotFoundException("Preset not found"));
        return presetMapper.toDetailResponse(preset);
    }

    @Override
    public Page<PresetCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<Preset> spec = presetSpecification.dynamic(filters);
        Page<Preset> page = presetRepository.findAll(spec, pageable);
        return presetMapper.toCompactResponse(page);
    }

    @Override
    public PresetCreateResponse create(PresetCreateRequest request) {
        Preset preset = presetMapper.toBouquet(request);
        preset = presetRepository.save(preset);
        return presetMapper.toCreateResponse(preset);
    }

    @Override
    public PresetUpdateResponse update(Long id, PresetUpdateRequest request) {
        Preset preset = presetMapper.toBouquet(request);
        preset.setId(id);
        preset = presetRepository.save(preset);
        return presetMapper.toUpdateResponse(preset);
    }

    @Override
    public void delete(Long id) {
        presetRepository.deleteById(id);
    }
}
