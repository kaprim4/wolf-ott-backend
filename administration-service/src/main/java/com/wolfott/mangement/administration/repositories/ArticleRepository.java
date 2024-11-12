package com.wolfott.mangement.administration.repositories;

import com.wolfott.mangement.administration.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
