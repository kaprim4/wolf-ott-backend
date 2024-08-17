package com.wolfott.mangement.server.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "output_formats")
public class OutputFormat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "output_format_seq")
    @SequenceGenerator(name = "output_format_seq", sequenceName = "output_format_seq", allocationSize = 1)
    @Column(name = "access_output_id")
    private Long accessOutputId;

    @Column(name = "output_name")
    private String outputName;

    @Column(name = "output_key", unique = true)
    private String outputKey;

    @Column(name = "output_ext", unique = true)
    private String outputExt;

    // #TODO: check if the primary key is correct

}

