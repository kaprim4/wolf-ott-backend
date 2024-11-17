package com.wolfott.mangement.administration.services;

import com.wolfott.mangement.administration.exceptions.ArticleNotFoundException;
import com.wolfott.mangement.administration.models.Application;
import com.wolfott.mangement.administration.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService
{
    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public List<Application> getAll() {
        return applicationRepository.findAll();
    }

    @Override
    public Application getOne(Long id) {
        return applicationRepository.findById(id).orElseThrow(ArticleNotFoundException::new);
    }

    @Override
    public Application create(Application article) {
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());
        return applicationRepository.save(article);
    }

    @Override
    public Application update(Long id, Application article) {
        article.setId(id);
        article.setUpdatedAt(LocalDateTime.now());
        return applicationRepository.save(article);
    }

    @Override
    public void delete(Long id) {
        applicationRepository.deleteById(id);
    }
}
