package com.wolfott.mangement.epg.services;

import com.wolfott.mangement.epg.exceptions.LanguageNotFoundException;
import com.wolfott.mangement.epg.mappers.LanguageMapper;
import com.wolfott.mangement.epg.models.EpgLanguage;
import com.wolfott.mangement.epg.repositories.LanguageRepository;
import com.wolfott.mangement.epg.requests.LanguageCreateRequest;
import com.wolfott.mangement.epg.requests.LanguageUpdateRequest;
import com.wolfott.mangement.epg.responses.LanguageCompactResponse;
import com.wolfott.mangement.epg.responses.LanguageCreateResponse;
import com.wolfott.mangement.epg.responses.LanguageDetailResponse;
import com.wolfott.mangement.epg.responses.LanguageUpdateResponse;
import com.wolfott.mangement.epg.specifications.LanguageSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    LanguageRepository languageRepository;
    @Autowired
    LanguageSpecification languageSpecification;
    @Autowired
    LanguageMapper languageMapper;

    @Override
    public LanguageDetailResponse getOne(Long id) {
        EpgLanguage language = languageRepository.findById(id).orElseThrow(() -> new LanguageNotFoundException("Language Not Found"))
        return languageMapper.toDetailResponse(language);
    }

    @Override
    public Page<LanguageCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<EpgLanguage> spec = languageSpecification.dynamic(filters);
        Page<EpgLanguage> page = languageRepository.findAll(spec, pageable);
        return languageMapper.toCompactResponse(page);
    }

    @Override
    public LanguageCreateResponse create(LanguageCreateRequest request) {
        EpgLanguage language = languageMapper.toLanguage(request);
        language = languageRepository.save(language);
        return languageMapper.toCreateResponse(language);
    }

    @Override
    public LanguageUpdateResponse update(Long id, LanguageUpdateRequest request) {
        EpgLanguage language = languageMapper.toLanguage(request);
        language.setId(id);
        language = languageRepository.save(language);
        return languageMapper.toUpdateResponse(language);
    }

    @Override
    public void delete(Long id) {
        languageRepository.deleteById(id);
    }
}
