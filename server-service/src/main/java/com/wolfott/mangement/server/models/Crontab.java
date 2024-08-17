package com.wolfott.mangement.server.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "crontab")
public class Crontab {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crontab_seq")
    @SequenceGenerator(name = "crontab_seq", sequenceName = "crontab_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "time", columnDefinition = "TEXT")
    private String time;

    @Column(name = "enabled")
    @ColumnDefault("false")
    private Boolean enabled;

}