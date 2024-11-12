package com.wolfott.mangement.administration.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.domain.Persistable;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Lob
    @Column(name = "content_text", columnDefinition = "LONGTEXT")
    private String content;
    @Lob
    @Column(name = "thumbnail_image", columnDefinition = "LONGTEXT")
    private String thumbnail;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;

}
