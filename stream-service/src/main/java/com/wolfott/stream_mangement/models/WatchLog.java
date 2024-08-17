package com.wolfott.stream_mangement.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "watch_logs")
public class WatchLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "watch_log_seq")
    @GenericGenerator(
            name = "watch_log_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "watch_log_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    @ColumnDefault("0")
    private Integer type;

    @Column(name = "server_id")
    @ColumnDefault("0")
    private Integer serverId;

    @Column(name = "filename")
    @ColumnDefault("NULL")
    private String filename;

    @Column(name = "status")
    @ColumnDefault("0")
    private Integer status;

    @Column(name = "stream_id")
    @ColumnDefault("0")
    private Integer streamId;

    @Column(name = "dateadded")
    @ColumnDefault("current_timestamp()")
    private Timestamp dateAdded;
}
