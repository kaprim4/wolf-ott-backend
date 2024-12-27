package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.models.Bouquet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BouquetService {
    Optional<Bouquet> findById(Long id);
    Bouquet save(Bouquet b);
    void deleteById(Long id);
    Page<Bouquet> findAllWithFilters(Map<String, Object> filters, Pageable pageable);
    List<Bouquet> findAllWithFilters(Map<String, Object> filters);
    List<Bouquet> findAllByIds(List<Long> ids);
    List<Bouquet> findAll();
}
