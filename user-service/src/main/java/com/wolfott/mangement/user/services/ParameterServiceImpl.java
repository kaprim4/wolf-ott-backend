package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.models.Parameter;
import com.wolfott.mangement.user.repositories.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterServiceImpl implements ParameterService {
    @Autowired
    private ParameterRepository parameterRepository;
    @Override
    public List<Parameter> getAll() {
        return parameterRepository.findAll();
    }

    @Override
    public Page<Parameter> getAll(Pageable pageable) {
        return parameterRepository.findAll(pageable);
    }
}
