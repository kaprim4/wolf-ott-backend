package com.wolfott.auth.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

//@Entity
@Data
@EqualsAndHashCode
@Table(name = "detect_restream_logs")
public class DetectRestreamLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detect_restream_log_seq")
    @GenericGenerator(
            name = "detect_restream_log_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "detect_restream_log_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "stream_id")
    private Long streamId;

    @Column(name = "ip")
    private String ip;

    @Column(name = "time")
    private Long time;

}