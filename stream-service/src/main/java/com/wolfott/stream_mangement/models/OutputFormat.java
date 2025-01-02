package com.wolfott.stream_mangement.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "output_formats")
public class OutputFormat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "access_output_id")
    private Integer id;

    @Column(name = "output_name")
    private String outputName ;

    @Column(name = "output_key")
    private String outputKey ;

    @Column(name = "output_ext")
    private String outputExt;

}