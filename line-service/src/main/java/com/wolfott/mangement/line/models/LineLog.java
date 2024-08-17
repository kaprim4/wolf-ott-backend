package com.wolfott.mangement.line.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

 @Entity
@Table(name = "lines_logs")
@Data
@NoArgsConstructor
public class LineLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "line_log_seq")
    @SequenceGenerator(name = "line_log_seq", sequenceName = "line_log_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "stream_id")
    private Long streamId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "client_status")
    private String clientStatus;

    @Lob
    @Column(name = "query_string", columnDefinition = "MEDIUMTEXT")
    private String queryString;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "ip")
    private String ip;

    @Lob
    @Column(name = "extra_data", columnDefinition = "MEDIUMTEXT")
    private String extraData;

    @Column(name = "date")
    private Long date;
}

