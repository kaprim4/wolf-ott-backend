package com.wolfott.stream_mangement.services;

import com.wolfott.stream_mangement.mappers.StreamMapper;
import com.wolfott.stream_mangement.models.Stream;
import com.wolfott.stream_mangement.repositories.StreamRepository;
import com.wolfott.stream_mangement.responses.StreamCompactResponse;
import com.wolfott.stream_mangement.specifications.StreamSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class StreamServiceImpl implements StreamService {

    @Autowired
    StreamRepository streamRepository;
    @Autowired
    StreamSpecification streamSpecification;
    @Autowired
    StreamMapper streamMapper;

    @Override
    public List<StreamCompactResponse> getAll(Map<String, Object> filters) {
        Specification<Stream> spec = streamSpecification.dynamic(filters);
        List<Stream> list = streamRepository.findAll(spec);
        return streamMapper.toCompactResponse(list);
    }

    @Override
    public Page<StreamCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<Stream> spec = streamSpecification.dynamic(filters);
        Page<Stream> page = streamRepository.findAll(spec, pageable);
        return streamMapper.toCompactResponse(page);
    }

    @Override
    public void delete(Long id) {
        streamRepository.deleteById(id);
    }
}
