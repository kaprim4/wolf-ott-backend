package com.wolfott.mangement.administration.services;

import com.wolfott.mangement.administration.models.Application;

import java.util.List;

public interface ApplicationService {

    List<Application> getAll();

    Application getOne(Long id);

    Application create(Application article);

    Application update(Long id, Application article);

    void delete(Long id);
}
