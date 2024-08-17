package com.wolfott.mangement.server.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "signals")
public class Signal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "signal_seq")
    @SequenceGenerator(name = "signal_seq", sequenceName = "signal_seq", allocationSize = 1)
    @Column(name = "signal_id")
    private Long signalId;

    @Column(name = "pid")
    private Integer pid;

    @Column(name = "server_id")
    private Integer serverId;

    @Column(name = "rtmp")
    @ColumnDefault("false")
    private Boolean rtmp;

    @Column(name = "time")
    private Integer time;

    @Lob
    @Column(name = "custom_data", columnDefinition = "MEDIUMTEXT")
    private String customData;

    @Column(name = "cache")
    @ColumnDefault("false")
    private Boolean cache;

}

