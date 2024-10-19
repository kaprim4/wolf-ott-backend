package com.wolfott.stream_mangement.services;

import com.wolfott.stream_mangement.exceptions.SerieNotFoundException;
import com.wolfott.stream_mangement.mappers.SerieMapper;
import com.wolfott.stream_mangement.models.StreamSeries;
import com.wolfott.stream_mangement.repositories.SerieRepository;
import com.wolfott.stream_mangement.responses.SerieCompactResponse;
import com.wolfott.stream_mangement.responses.SerieDetailResponse;
import com.wolfott.stream_mangement.specifications.SerieSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SerieServiceImpl implements SerieService {
    @Autowired
    private SerieRepository serieRepository;
    @Autowired
    private SerieSpecification serieSpecification;
    @Autowired
    private SerieMapper serieMapper;
    @Override
    public SerieDetailResponse getOne(Long id) {
        StreamSeries serie = serieRepository.findById(id).orElseThrow(SerieNotFoundException::new);
        return serieMapper.toSerieDetailResponse(serie);
    }

    @Override
    public List<SerieCompactResponse> getAll() {
        List<StreamSeries> series = serieRepository.findAll();
        return serieMapper.toSerieCompactResponseList(series);
    }

    @Override
    public List<SerieCompactResponse> getAll(Map<String, Object> filters) {
        Specification<StreamSeries> spec = serieSpecification.dynamic(filters);
        List<StreamSeries> series = serieRepository.findAll(spec);
        return serieMapper.toSerieCompactResponseList(series);
    }

    @Override
    public Page<SerieCompactResponse> getAll(Pageable pageable) {
        Page<StreamSeries> series = serieRepository.findAll(pageable);
        return serieMapper.toSerieCompactResponsePage(series);
    }

    @Override
    public Page<SerieCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<StreamSeries> spec = serieSpecification.dynamic(filters);
        Page<StreamSeries> series = serieRepository.findAll(spec, pageable);
        return serieMapper.toSerieCompactResponsePage(series);
    }

}
