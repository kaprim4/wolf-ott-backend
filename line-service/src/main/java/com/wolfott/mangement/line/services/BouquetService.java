package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.models.Bouquet;
import com.wolfott.mangement.line.requests.PresetBouquetCategoryCreateRequest;
import com.wolfott.mangement.line.requests.PresetBouquetCategoryUpdateRequest;
import com.wolfott.mangement.line.responses.CategoryCompactResponse;
import com.wolfott.mangement.line.responses.PresetBouquetCategoryCreateResponse;
import com.wolfott.mangement.line.responses.PresetBouquetCategoryDetailResponse;
import com.wolfott.mangement.line.responses.PresetBouquetCategoryUpdateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BouquetService {
    Optional<Bouquet> findById(Long id);
    List<CategoryCompactResponse> getBouquetCategories(Long id);
    List<CategoryCompactResponse> getBouquetCategories(Long id, String type);
    Bouquet save(Bouquet b);
    void deleteById(Long id);
    Page<Bouquet> findAllWithFilters(Map<String, Object> filters, Pageable pageable);
    List<Bouquet> findAllWithFilters(Map<String, Object> filters);
    List<Bouquet> findAllByIds(List<Long> ids);
    List<Bouquet> findAll();

    Page<PresetBouquetCategoryDetailResponse> getAllBouquetsPresets(Pageable pageable);
    PresetBouquetCategoryCreateResponse savePresetBouquetCategory(PresetBouquetCategoryCreateRequest request);
    PresetBouquetCategoryCreateResponse savePresetBouquetCategory(Long bouquetId, PresetBouquetCategoryCreateRequest request);

    PresetBouquetCategoryUpdateResponse updatePresetBouquetCategory(Long id, PresetBouquetCategoryUpdateRequest request);

    PresetBouquetCategoryDetailResponse getBouquetPreset(Long id);
}
