package com.wolfott.mangement.line.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "bouquets")
public class Bouquet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bouquet_name")
    private String bouquetName;

    @Lob
    @Column(name = "bouquet_channels", columnDefinition = "MEDIUMTEXT")
    private String bouquetChannels;

    @Lob
    @Column(name = "bouquet_movies", columnDefinition = "MEDIUMTEXT")
    private String bouquetMovies;

    @Lob
    @Column(name = "bouquet_radios", columnDefinition = "MEDIUMTEXT")
    private String bouquetRadios;

    @Lob
    @Column(name = "bouquet_series", columnDefinition = "MEDIUMTEXT")
    private String bouquetSeries;

    @Column(name = "bouquet_order")
    @ColumnDefault("0")
    private Integer bouquetOrder;
}
