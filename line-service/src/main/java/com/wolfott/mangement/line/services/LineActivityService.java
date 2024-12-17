package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.requests.LineChartResponse;
import com.wolfott.mangement.line.responses.LineActivityCompactResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.*;

public interface LineActivityService {

    Page<LineActivityCompactResponse> getAllByUserId(Long id, Pageable pageable);

    List<LineChartResponse> getLineChart(Long limit);
}
