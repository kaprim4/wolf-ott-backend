package com.wolfott.stream_mangement.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;

@Data
//@Entity
@Table(name = "watch_refresh")
public class WatchRefresh {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "watch_refresh_seq")
    @SequenceGenerator(name = "watch_refresh_seq", sequenceName = "watch_refresh_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    @ColumnDefault("0")
    private Integer type;

    @Column(name = "stream_id")
    @ColumnDefault("0")
    private Integer streamId;

    @Column(name = "status")
    @ColumnDefault("0")
    private Integer status;

    @Column(name = "dateadded")
    @ColumnDefault("current_timestamp()")
    private Timestamp dateAdded;
}
