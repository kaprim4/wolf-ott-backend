package com.wolfott.mangement.server.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Data
@Entity
@Table(name = "panel_stats")
public class PanelStat implements Serializable {

    @Id
    @GeneratedValue(generator = "panel_stats_generator")
    @GenericGenerator(
            name = "panel_stats_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "panel_stats_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "time")
    @ColumnDefault("0")
    private Integer time;

    @Column(name = "count")
    @ColumnDefault("0")
    private Float count;

    @Column(name = "server_id")
    @ColumnDefault("0")
    private Integer serverId;
}