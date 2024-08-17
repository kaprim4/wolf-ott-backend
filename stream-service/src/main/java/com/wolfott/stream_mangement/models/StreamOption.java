package com.wolfott.stream_mangement.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "streams_options")
public class StreamOption {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stream_option_seq")
    @SequenceGenerator(name = "stream_option_seq", sequenceName = "stream_option_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "stream_id")
    private Long streamId;

    @Column(name = "argument_id")
    private Long argumentId;

    @Lob
    @Column(name = "value", columnDefinition = "TEXT")
    private String value;

    @Column(name = "active")
    @ColumnDefault("false")
    private Boolean active;

}