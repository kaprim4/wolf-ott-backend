package com.wolfott.mangement.administration.controllers;

import com.wolfott.mangement.administration.models.Article;
import com.wolfott.mangement.administration.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping
    public Page<Article> getAll(Pageable pageable){
        return articleService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Article getAll(@PathVariable("id") Long id){
        return articleService.getOne(id);
    }

    @PostMapping
    public Article create(@RequestBody Article article){
        return articleService.create(article);
    }
    @PutMapping("/{id}")
    public Article create(@PathVariable("id") Long id, @RequestBody Article article){
        return articleService.update(id, article);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        articleService.delete(id);
    }
}
