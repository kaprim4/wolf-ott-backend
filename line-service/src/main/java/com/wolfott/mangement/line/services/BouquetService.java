package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.models.Bouquet;
import com.wolfott.mangement.line.repositories.BouquetRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BouquetService {

    @Autowired
    private BouquetRepository bouquetRepository;

    public Optional<Bouquet> findById(Long id){
        return bouquetRepository.findById(id);
    }

    public Bouquet save(Bouquet b) {
        return bouquetRepository.save(b);
    }

    public void deleteById(Long id){
        bouquetRepository.deleteById(id);
    }

    public Page<Bouquet> findAllWithFilters(Map<String, Object> filters, Pageable pageable) {
        Specification<Bouquet> spec = buildSpecification(filters);
        return bouquetRepository.findAll(spec, pageable);
    }

    public List<Bouquet> findAllWithFilters(Map<String, Object> filters) {
        Specification<Bouquet> spec = buildSpecification(filters);
        return bouquetRepository.findAll(spec);
    }

    public List<Bouquet> findAllByIds(List<Long> ids) {
        return bouquetRepository.findAllById(ids);
    }

    public List<Bouquet> findAll() {
        return bouquetRepository.findAll();
    }

    private Specification<Bouquet> buildSpecification(Map<String, Object> filters) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (filters.containsKey("bouquetName")) {
                String bouquetName = filters.get("bouquetName").toString();
                predicate = cb.and(predicate, cb.like(root.get("bouquetName"), "%" + bouquetName + "%"));
            }
            if (filters.containsKey("bouquetOrder")) {
                Object bouquetOrderObj = filters.get("bouquetOrder");
                Integer bouquetOrder = Integer.valueOf(bouquetOrderObj.toString());
                predicate = cb.and(predicate, cb.equal(root.get("bouquetOrder"), bouquetOrder));
            }
            return predicate;
        };
    }
}