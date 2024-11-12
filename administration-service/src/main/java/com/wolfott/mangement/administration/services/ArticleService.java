package com.wolfott.mangement.administration.services;

import com.wolfott.mangement.administration.models.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {
    List<Article> getAll();
    Page<Article> getAll(Pageable pageable);
    Article getOne(Long id);
    Article create(Article article);
    Article update(Long id, Article article);
    void delete(Long id);
}
