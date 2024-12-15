package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.models.Bouquet;
import com.wolfott.mangement.line.models.Preset;
import com.wolfott.mangement.line.repositories.BouquetRepository;
import com.wolfott.mangement.line.repositories.PresetRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class PresetService {

    @Autowired
    private PresetRepository presetRepository;

    public Optional<Preset> findById(Long id){
        return presetRepository.findById(id);
    }

    public Preset save(Preset p) {
        return presetRepository.save(p);
    }

    public void deleteById(Long id){
        presetRepository.deleteById(id);
    }

    public Page<Preset> findAllWithFilters(Map<String, Object> filters, Pageable pageable) {
        Specification<Preset> spec = buildSpecification(filters);
        return presetRepository.findAll(spec, pageable);
    }

    public List<Preset> findAllWithFilters(Map<String, Object> filters) {
        Specification<Preset> spec = buildSpecification(filters);
        return presetRepository.findAll(spec);
    }

    private Specification<Preset> buildSpecification(Map<String, Object> filters) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (filters.containsKey("presetName")) {
                String presetName = filters.get("presetName").toString();
                // Filtrage par nom avec un LIKE
                predicate = cb.and(predicate, cb.like(root.get("presetName"), "%" + presetName + "%"));
            }
            if (filters.containsKey("status")) {
                Object statusObj = filters.get("status");
                boolean status = Boolean.parseBoolean(statusObj.toString());
                predicate = cb.and(predicate, cb.equal(root.get("status"), status));
            }
            return predicate;
        };
    }
}