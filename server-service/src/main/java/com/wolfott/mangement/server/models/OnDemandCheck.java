package com.wolfott.mangement.server.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ondemand_check")
public class OnDemandCheck {

    @Id
    @GeneratedValue(generator = "ondemand_check_id_seq")
    @GenericGenerator(
            name = "ondemand_check_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "ondemand_check_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "stream_id")
    private Long streamId;

    @Column(name = "server_id")
    private Long serverId;

    @Column(name = "status")
    @ColumnDefault("0")
    private Integer status;

    @Column(name = "source_id")
    private Integer sourceId;

    @Column(name = "source_url")
    private String sourceUrl;

    @Column(name = "video_codec")
    private String videoCodec;

    @Column(name = "audio_codec")
    private String audioCodec;

    @Column(name = "resolution")
    private String resolution;

    @Column(name = "response")
    private Integer response;

    @Column(name = "fps")
    private Integer fps;

    @Lob
    @Column(name = "errors", columnDefinition = "MEDIUMTEXT")
    private String errors;

    @Column(name = "date")
    private Timestamp date;
}
