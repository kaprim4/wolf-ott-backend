package com.wolfott.auth.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

//@Entity
@Data
@NoArgsConstructor
@Table(name = "blocked_isps")
public class BlockedIsp {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blocked_isps_seq")
    @SequenceGenerator(name = "blocked_isps_seq", sequenceName = "blocked_isps_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "isp", columnDefinition = "MEDIUMTEXT")
    private String isp;

    @Column(name = "blocked")
    @ColumnDefault("false")
    private Boolean blocked;
}
