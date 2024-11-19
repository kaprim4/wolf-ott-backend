package com.wolfott.mangement.administration.services;

import com.wolfott.mangement.administration.exceptions.ArticleNotFoundException;
import com.wolfott.mangement.administration.models.Article;
import com.wolfott.mangement.administration.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Article::getId).reversed())
                .toList();
    }

    @Override
    public Page<Article> getAll(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @Override
    public Article getOne(Long id) {
        return articleRepository.findById(id).orElseThrow(ArticleNotFoundException::new);
    }

    @Override
    public Article create(Article article) {
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());
        return articleRepository.save(article);
    }

    @Override
    public Article update(Long id, Article article) {
        Optional<Article> article1 = articleRepository.findById(id);
        if (article1.isPresent()) {
            article.setId(id);
            article.setCreatedAt(article1.get().getCreatedAt());
            article.setUpdatedAt(LocalDateTime.now());
            return articleRepository.save(article);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        articleRepository.deleteById(id);
    }
}
