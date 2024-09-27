package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.configs.UserServiceClient;
import com.wolfott.mangement.line.exceptions.LineNotFoundException;
import com.wolfott.mangement.line.mappers.LineMapper;
import com.wolfott.mangement.line.models.Line;
import com.wolfott.mangement.line.models.LineList;
import com.wolfott.mangement.line.models.User;
import com.wolfott.mangement.line.repositories.LineRepository;
import com.wolfott.mangement.line.requests.LineCreateRequest;
import com.wolfott.mangement.line.requests.LineUpdateRequest;
import com.wolfott.mangement.line.responses.LineCompactResponse;
import com.wolfott.mangement.line.responses.LineCreateResponse;
import com.wolfott.mangement.line.responses.LineDetailResponse;
import com.wolfott.mangement.line.responses.LineUpdateResponse;
import com.wolfott.mangement.line.specifications.LineSpecifications;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
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
    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    public LineDetailResponse getOne(Long id) {
        Line line = lineRepository.findById(id).orElseThrow(() -> new LineNotFoundException("Line not found"));
        return lineMapper.toLineDetailResponse(line);
    }

    @Override
    public int getLinesCount() {
        return 0;
    }

    @Override
    public Page<LineCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<Line> spec = lineSpecifications.dynamic(filters);
        Page<Line> page = lineRepository.findAll(spec, pageable);
//        List<Line> lines = page.getContent().stream().parallel()
//                .map(line -> {
//                    User member = new User();
//                    String username = userServiceClient.getUsernameByMemberId(line.getMemberId());
//                    member.setUsername(username);
//                    line.setMember(member);
//                    return line;
//                }).toList();
//        page = new PageImpl<>(
//                lines,
//                pageable,
//                page.getTotalElements()
//        );
        return lineMapper.toLineCompactResponsePage(page);
    }

    @Override
    public Page<LineList> getAllforListing(Map<String, Object> filters, Pageable pageable) {
        return null;
    }

    @Override
    public LineCreateResponse create(LineCreateRequest request) {
        Line line = lineMapper.toLine(request);
        line = lineRepository.save(line);
        return lineMapper.toLineCreateResponse(line);
    }

    @Override
    public LineUpdateResponse update(Long id, LineUpdateRequest request) {
        Line line = lineRepository.findById(id).orElseThrow(() -> new LineNotFoundException("Line not found"));
        line = lineMapper.mergeLine(request, line);
        line = lineRepository.save(line);
        return lineMapper.toLineUpdateResponse(line);
    }

    @Override
    public void delete(Long id) {
        lineRepository.deleteById(id);
    }
}