package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.exceptions.BouquetNotFoundException;
import com.wolfott.mangement.line.mappers.BouquetMapper;
import com.wolfott.mangement.line.models.Bouquet;
import com.wolfott.mangement.line.repositories.BouquetRepository;
import com.wolfott.mangement.line.requests.BouquetCreateRequest;
import com.wolfott.mangement.line.requests.BouquetUpdateRequest;
import com.wolfott.mangement.line.responses.BouquetCompactResponse;
import com.wolfott.mangement.line.responses.BouquetCreateResponse;
import com.wolfott.mangement.line.responses.BouquetDetailResponse;
import com.wolfott.mangement.line.responses.BouquetUpdateResponse;
import com.wolfott.mangement.line.specifications.BouquetSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BouquetServiceImpl implements BouquetService {

    @Autowired
    BouquetRepository bouquetRepository;
    @Autowired
    BouquetSpecification bouquetSpecification;
    @Autowired
    BouquetMapper bouquetMapper;

    @Override
    public BouquetDetailResponse getOne(Long id) {
        Bouquet bouquet = bouquetRepository.findById(id).orElseThrow(() -> new BouquetNotFoundException("Bouquet Not Found"));
        return bouquetMapper.toDetailResponse(bouquet);
    }

    @Override
    public List<BouquetCompactResponse> getAll(Map<String, Object> filters) {
        Specification<Bouquet> spec = bouquetSpecification.dynamic(filters);
        List<Bouquet> list = bouquetRepository.findAll(spec);
        return bouquetMapper.toCompactResponse(list);
    }

    @Override
    public Page<BouquetCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<Bouquet> spec = bouquetSpecification.dynamic(filters);
        Page<Bouquet> page = bouquetRepository.findAll(spec, pageable);
        return bouquetMapper.toCompactResponse(page);
    }

    @Override
    public BouquetCreateResponse create(BouquetCreateRequest request) {
        Bouquet bouquet = bouquetMapper.toBouquet(request);
        bouquet = bouquetRepository.save(bouquet);
        return bouquetMapper.toCreateResponse(bouquet);
    }

    @Override
    public BouquetUpdateResponse update(Long id, BouquetUpdateRequest request) {
        Bouquet bouquet = bouquetMapper.toBouquet(request);
        bouquet.setId(id);
        bouquet = bouquetRepository.save(bouquet);
        return bouquetMapper.toUpdateResponse(bouquet);
    }

    @Override
    public void delete(Long id) {
        bouquetRepository.deleteById(id);
    }
}
