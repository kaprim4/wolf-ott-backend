package com.wolfott.stream_mangement.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

//@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "streams_errors")
public class StreamError {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "error_seq")
    @SequenceGenerator(name = "error_seq", sequenceName = "error_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "stream_id")
    private Long streamId;

    @Column(name = "server_id")
    private Long serverId;

    @Column(name = "date")
    private Date date;

    @Column(name = "error", length = 500)
    @Lob
    private String error;

    @Column(name = "is_active")
    @ColumnDefault("false")
    private Boolean active;

}
