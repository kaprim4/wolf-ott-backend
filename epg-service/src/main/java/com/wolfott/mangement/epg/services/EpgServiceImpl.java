package com.wolfott.mangement.epg.services;

import com.wolfott.mangement.epg.exceptions.EpgNotFoundException;
import com.wolfott.mangement.epg.mappers.EpgMapper;
import com.wolfott.mangement.epg.models.Epg;
import com.wolfott.mangement.epg.repositories.EpgRepository;
import com.wolfott.mangement.epg.requests.EpgCreateRequest;
import com.wolfott.mangement.epg.requests.EpgUpdateRequest;
import com.wolfott.mangement.epg.responses.EpgCompactResponse;
import com.wolfott.mangement.epg.responses.EpgCreateResponse;
import com.wolfott.mangement.epg.responses.EpgDetailResponse;
import com.wolfott.mangement.epg.responses.EpgUpdateResponse;
import com.wolfott.mangement.epg.specifications.EpgSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EpgServiceImpl implements EpgService {

    @Autowired
    EpgRepository epgRepository;
    @Autowired
    EpgSpecification epgSpecification;
    @Autowired
    EpgMapper epgMapper;

    @Override
    public EpgDetailResponse getOne(Long id) {
        Epg epg = epgRepository.findById(id).orElseThrow(() -> new EpgNotFoundException("EPG Not Found"));
        return epgMapper.toDetailResponse(epg);
    }

    @Override
    public Page<EpgCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<Epg> spec = epgSpecification.dynamic(filters);
        Page<Epg> page = epgRepository.findAll(spec, pageable);
        return epgMapper.toCompactResponse(page);
    }

    @Override
    public EpgCreateResponse create(EpgCreateRequest request) {
        Epg epg = epgMapper.toEpg(request);
        epg = epgRepository.save(epg);
        return epgMapper.toCreateResponse(epg);
    }

    @Override
    public EpgUpdateResponse update(Long id, EpgUpdateRequest request) {
        Epg epg = epgMapper.toEpg(request);
        epg.setId(id);
        epg = epgRepository.save(epg);
        return epgMapper.toUpdateResponse(epg);
    }

    @Override
    public void delete(Long id) {
        epgRepository.deleteById(id);
    }
}
