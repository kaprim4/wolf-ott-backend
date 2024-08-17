package com.wolfott.stream_mangement.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "watch_categories")
public class WatchCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "watch_category_seq")
    @SequenceGenerator(name = "watch_category_seq", sequenceName = "watch_category_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "type", columnDefinition = "int default 0")
    private Integer type;

    @Column(name = "genre_id", columnDefinition = "int default 0")
    private Integer genreId;

    @Column(name = "genre", columnDefinition = "varchar(64) default null")
    private String genre;

    @Column(name = "category_id", columnDefinition = "int default 0")
    private Integer categoryId;

    @Lob
    @Column(name = "bouquets", columnDefinition = "varchar(4096) default null")
    private String bouquets;
}
