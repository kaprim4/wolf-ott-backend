package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.exceptions.LineNotFoundException;
import com.wolfott.mangement.line.mappers.LineMapper;
import com.wolfott.mangement.line.models.Line;
import com.wolfott.mangement.line.models.LineListDto;
import com.wolfott.mangement.line.repositories.LineActivityRepository;
import com.wolfott.mangement.line.repositories.LineRepository;
import com.wolfott.mangement.line.requests.LineCreateRequest;
import com.wolfott.mangement.line.requests.LineUpdateRequest;
import com.wolfott.mangement.line.responses.*;
import com.wolfott.mangement.line.specifications.LineSpecifications;
import com.wolfott.mangement.user.models.User;
import com.wolfott.mangement.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LineServiceImpl implements LineService {

    @Autowired
    LineRepository lineRepository;

    @Autowired
    LineActivityRepository lineActivityRepository;

    UserRepository userRepository;

    @Autowired
    LineSpecifications lineSpecifications;

    @Autowired
    LineMapper lineMapper;

    @Override
    public LineDetailResponse getOne(Long id) {
        Line line = lineRepository.findById(id).orElseThrow(() -> new LineNotFoundException("Line not found"));
        return lineMapper.toLineDetailResponse(line);
    }

    public Page<LineListDto> convertListToPage(List<LineListDto> lineList, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), lineList.size());
        List<LineListDto> sublist = lineList.subList(start, end);
        return new PageImpl<>(sublist, pageable, lineList.size());
    }

    @Override
    public int getLinesCount() {
        return lineRepository.findAll().size();
    }

    @Override
    public Page<LineCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<Line> spec = lineSpecifications.dynamic(filters);
        Page<Line> linePage = lineRepository.findAll(spec, pageable);
        return lineMapper.toLineCompactResponsePage(linePage);
    }

    @Override
    public Page<LineListDto> getAllforListing(Map<String, Object> filters, Pageable pageable) {
        Specification<Line> spec = lineSpecifications.dynamic(filters);
        Page<Line> linePage = lineRepository.findAll(spec, pageable);
        List<LineListDto> lineList = new ArrayList<>();
        for (Line line : linePage.getContent()) {
            User owner = userRepository.findOneById(line.getMemberId());
            int count_cnx = lineActivityRepository.findCountByLineID(line.getLastActivity());
            LineListDto lineListDto = LineListDto.builder()
                    .id(line.getId())
                    .username(line.getUsername())
                    .password(line.getPassword())
                    .owner(owner.getUsername())
                    .status(line.getEnabled())
                    .online(line.getLastActivity() != null)
                    .trial(line.getIsTrial())
                    .active(line.getAdminEnabled())
                    .connections(count_cnx)
                    .expiration(line.getExpDate())
                    .lastConnection(line.getUpdated())
                    .build();
            lineList.add(lineListDto);
        }
        return convertListToPage(lineList, pageable);
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
