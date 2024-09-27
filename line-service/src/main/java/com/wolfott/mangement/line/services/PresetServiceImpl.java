package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.exceptions.BouquetNotFoundException;
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
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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
    public List<PresetCompactResponse> getAll(Map<String, Object> filters) {
        Specification<Preset> spec = presetSpecification.dynamic(filters);
        List<Preset> list = presetRepository.findAll(spec);
        return presetMapper.toCompactResponse(list);
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
        preset.setCreatedAt(LocalDateTime.now());
        preset.setUpdatedAt(LocalDateTime.now());
        preset = presetRepository.save(preset);
        return presetMapper.toCreateResponse(preset);
    }

    @Override
    @Transactional
    public PresetUpdateResponse update(final Long id, PresetUpdateRequest request) {
        Preset preset = presetRepository.findById(id).orElseThrow(() -> new PresetNotFoundException("Preset Not Found"));
        preset = presetMapper.merge(request, preset);
        preset.setUpdatedAt(LocalDateTime.now());
        preset.getPresetBouquets().forEach(presetBouquet -> {
            presetBouquet.setPreset(new Preset(id));
        });
        try {
            preset = presetRepository.save(preset);
        }catch (JpaObjectRetrievalFailureException | EntityNotFoundException ex){
            String msg = ex.getMessage();
            msg = msg.replaceAll("com.wolfott.mangement.line.models.Bouquet", "Bouquet");
            throw new BouquetNotFoundException(msg);
        }
        return presetMapper.toUpdateResponse(preset);
    }

    @Override
    public void delete(Long id) {
        presetRepository.deleteById(id);
    }
}
