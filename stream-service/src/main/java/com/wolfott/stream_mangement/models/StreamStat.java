package com.wolfott.stream_mangement.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "streams_stats")
public class StreamStat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stream_stats_seq")
    @SequenceGenerator(name = "stream_stats_seq", sequenceName = "stream_stats_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "stream_id")
    @ColumnDefault("0")
    private Long streamId;

    @Column(name = "rank")
    @ColumnDefault("0")
    private Integer rank;

    @Column(name = "time")
    @ColumnDefault("0")
    private Integer time;

    @Column(name = "connections")
    @ColumnDefault("0")
    private Integer connections;

    @Column(name = "users")
    @ColumnDefault("0")
    private Integer users;

    @Column(name = "type")
    private String type;

    @Column(name = "dateadded", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp dateAdded;
}
