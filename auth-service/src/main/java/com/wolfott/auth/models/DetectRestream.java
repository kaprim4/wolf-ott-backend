package com.wolfott.auth.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

//@Entity
@Data
@NoArgsConstructor
@Table(name = "detect_restream")
public class DetectRestream {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detect_restream_seq")
    @SequenceGenerator(name = "detect_restream_seq", sequenceName = "detect_restream_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "ip")
    private String ip;

    @Column(name = "blocked")
    @ColumnDefault("false")
    private Boolean blocked;

    @Lob
    @Column(name = "ports_open", columnDefinition = "MEDIUMTEXT")
    private String portsOpen;

    @Column(name = "time")
    private Integer time;
}
