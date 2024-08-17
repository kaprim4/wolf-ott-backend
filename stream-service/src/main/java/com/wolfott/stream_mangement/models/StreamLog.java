package com.wolfott.stream_mangement.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "streams_logs")
public class StreamLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "streams_logs_seq")
    @SequenceGenerator(name = "streams_logs_seq", sequenceName = "streams_logs_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "stream_id")
    private Long streamId;

    @Column(name = "server_id")
    private Long serverId;

    @Column(name = "action", length = 500)
    private String action;

    @Column(name = "source", length = 1024)
    private String source;

    @Column(name = "date")
    private Date date;

}
