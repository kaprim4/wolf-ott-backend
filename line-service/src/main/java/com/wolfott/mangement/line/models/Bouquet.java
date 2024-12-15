package com.wolfott.mangement.line.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "bouquets")
@AllArgsConstructor
@NoArgsConstructor
public class Bouquet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bouquet_name", columnDefinition = "mediumtext")
    private String bouquetName;

    @Column(name = "bouquet_channels", columnDefinition = "mediumtext")
    private String bouquetChannels;

    @Column(name = "bouquet_movies", columnDefinition = "mediumtext")
    private String bouquetMovies;

    @Column(name = "bouquet_radios", columnDefinition = "mediumtext")
    private String bouquetRadios;

    @Column(name = "bouquet_series", columnDefinition = "mediumtext")
    private String bouquetSeries;

    @Column(name = "bouquet_order")
    private Integer bouquetOrder;

    @Column(name = "position_order")
    private Integer positionOrder;

    @OneToMany(mappedBy = "bouquet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PresetBouquet> presetBouquets = new HashSet<>();

    public Bouquet(Long id) {
        this.id = id;
    }
}