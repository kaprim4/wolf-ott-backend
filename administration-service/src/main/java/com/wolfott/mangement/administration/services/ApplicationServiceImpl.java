package com.wolfott.mangement.administration.services;

import com.wolfott.mangement.administration.exceptions.ArticleNotFoundException;
import com.wolfott.mangement.administration.models.Application;
import com.wolfott.mangement.administration.models.Article;
import com.wolfott.mangement.administration.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public Page<Application> getAll(Pageable pageable) {
        return applicationRepository.findAll(pageable);
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
    public Application update(Long id, Application application) {
        Optional<Application> application1 = applicationRepository.findById(id);
        if (application1.isPresent()) {
            application.setId(id);
            application.setCreatedAt(application1.get().getCreatedAt());
            application.setUpdatedAt(LocalDateTime.now());
            return applicationRepository.save(application);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        applicationRepository.deleteById(id);
    }
}
