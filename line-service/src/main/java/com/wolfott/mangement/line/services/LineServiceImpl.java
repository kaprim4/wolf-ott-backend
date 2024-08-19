package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.exceptions.LineNotFoundException;
import com.wolfott.mangement.line.mappers.LineMapper;
import com.wolfott.mangement.line.models.Line;
import com.wolfott.mangement.line.repositories.LineRepository;
import com.wolfott.mangement.line.responses.LineCompactResponse;
import com.wolfott.mangement.line.responses.LineDetailResponse;
import com.wolfott.mangement.line.specifications.LineSpecifications;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LineServiceImpl implements LineService {

    @Autowired
    LineRepository lineRepository;
    @Autowired
    LineSpecifications lineSpecifications;
    @Autowired
    LineMapper lineMapper;

    @Override
    public LineDetailResponse getOne(Long id) {
        Line line = lineRepository.findById(id).orElseThrow(() -> new LineNotFoundException("Line not found"));
        return lineMapper.toLineDetailResponse(line);
    }

    @Override
    public Page<LineCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<Line> spec = lineSpecifications.dynamic(filters);
        Page<Line> linePage = lineRepository.findAll(spec, pageable);
        return lineMapper.toLineCompactResponsePage(linePage);
    }
}
