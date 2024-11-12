package com.wolfott.mangement.administration.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.domain.Persistable;

@Entity
@Data
@Table(name = "articles")
public class Article implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String imgPath;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return !(this.id > 0);
    }
}
