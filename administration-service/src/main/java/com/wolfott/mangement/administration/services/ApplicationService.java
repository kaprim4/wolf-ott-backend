package com.wolfott.mangement.administration.services;

import com.wolfott.mangement.administration.models.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicationService {

    List<Application> getAll();
    Page<Application> getAll(Pageable pageable);

    Application getOne(Long id);

    Application create(Application article);

    Application update(Long id, Application article);

    void delete(Long id);
}
