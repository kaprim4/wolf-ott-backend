package com.wolfott.mangement.administration.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "rankings")
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String title;
    private Integer minPoints;
    private Integer maxPoints;
    @Lob
    @Column(name = "badge_image", columnDefinition = "LONGTEXT")
    private String badgeImage;
}
