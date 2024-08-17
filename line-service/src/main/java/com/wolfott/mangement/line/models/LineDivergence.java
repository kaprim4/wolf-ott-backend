package com.wolfott.mangement.line.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Table(name = "lines_divergence")
public class LineDivergence {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private String id;

    @Column(name = "uuid", unique = true)
    private String uuid;

    @Column(name = "divergence")
    private Float divergence;

}