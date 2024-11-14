package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.models.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ParameterService {
    List<Parameter> getAll();
    Page<Parameter> getAll(Pageable pageable);
}
