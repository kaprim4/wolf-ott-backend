package com.wolfott.stream_mangement.services;

import com.wolfott.stream_mangement.exceptions.StreamNotFoundException;
import com.wolfott.stream_mangement.mappers.StreamMapper;
import com.wolfott.stream_mangement.models.Stream;
import com.wolfott.stream_mangement.models.StreamType;
import com.wolfott.stream_mangement.repositories.StreamRepository;
import com.wolfott.stream_mangement.repositories.TypeRepository;
import com.wolfott.stream_mangement.responses.StreamCompactResponse;
import com.wolfott.stream_mangement.responses.StreamDetailResponse;
import com.wolfott.stream_mangement.specifications.StreamSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StreamServiceImpl implements StreamService {
    @Autowired
    StreamRepository streamRepository;
    @Autowired
    TypeRepository typeRepository;
    @Autowired
    StreamMapper streamMapper;
    @Autowired
    StreamSpecification streamSpecification;
    @Override
    public StreamDetailResponse getOne(Long id) {
        Stream stream = streamRepository.findById(id).orElseThrow(StreamNotFoundException::new);
        return streamMapper.toStreamDetailResponse(stream);
    }

    @Override
    public List<StreamCompactResponse> getAll() {
        List<Stream> streams = streamRepository.findAll();
        return streamMapper.toStreamCompactResponseList(streams);
    }

    @Override
    public List<StreamCompactResponse> getAll(Map<String, Object> filters) {
        Specification<Stream> spec = streamSpecification.dynamic(filters);
        List<Stream> streams = streamRepository.findAll(spec);
        return streamMapper.toStreamCompactResponseList(streams);
    }

    @Override
    public Page<StreamCompactResponse> getAll(Pageable pageable) {
        Page<Stream> streams = streamRepository.findAll(pageable);
        return streamMapper.toStreamCompactResponsePage(streams);
    }

    @Override
    public Page<StreamCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<Stream> spec = streamSpecification.dynamic(filters);
        Page<Stream> streams = streamRepository.findAll(spec, pageable);
        return streamMapper.toStreamCompactResponsePage(streams);
    }

    @Override
    public Page<StreamCompactResponse> getAll(String type, Map<String, Object> filters, Pageable pageable) {
        Specification<Stream> spec = streamSpecification.dynamic(filters);
        Page<Stream> streams = streamRepository.findByType_TypeKey(type, spec, pageable);
        return streamMapper.toStreamCompactResponsePage(streams);
    }

    @Override
    public Page<StreamCompactResponse> getAllByType(String type, Pageable pageable) {
        Page<Stream> streams = streamRepository.findByType_TypeKey(type, pageable);
        return streamMapper.toStreamCompactResponsePage(streams);
    }

    @Override
    public Page<StreamCompactResponse> getAllByType(String type, Map<String, Object> filters, Pageable pageable) {
        return null;
    }

    @Override
    public List<StreamType> getTypes() {
        return typeRepository.findAll();
    }
}
