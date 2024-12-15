package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.responses.LineActivityCompactResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LineActivityService {

    Page<LineActivityCompactResponse> getAllByUserId(Long id, Pageable pageable);
}
