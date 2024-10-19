package com.wolfott.stream_mangement.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

//@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "streams_servers")
public class StreamServer {

    @Id
    @GeneratedValue(generator = "server_stream_id_seq")
    @GenericGenerator(
            name = "server_stream_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "server_stream_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "server_stream_id")
    private Long id;

    @Column(name = "stream_id")
    private Long streamId;

    @Column(name = "server_id")
    private Long serverId;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "pid")
    private Long pid;

    @Column(name = "to_analyze")
    @ColumnDefault("false")
    private Boolean toAnalyze;

    @Column(name = "stream_status")
    private Long streamStatus;

    @Column(name = "stream_started")
    private Long streamStarted;

    @Lob
    @Column(name = "stream_info", columnDefinition = "MEDIUMTEXT")
    private String streamInfo;

    @Column(name = "monitor_pid")
    private Long monitorPid;

    @Column(name = "aes_pid")
    private Long aesPid;

    @Lob
    @Column(name = "current_source", columnDefinition = "MEDIUMTEXT")
    private String currentSource;

    @Column(name = "bitrate")
    private Long bitrate;

    @Lob
    @Column(name = "progress_info", columnDefinition = "MEDIUMTEXT")
    private String progressInfo;

    @Lob
    @Column(name = "cc_info", columnDefinition = "MEDIUMTEXT")
    private String ccInfo;

    @Column(name = "on_demand")
    @ColumnDefault("false")
    private Boolean onDemand;

    @Column(name = "delay_pid")
    private Long delayPid;

    @Column(name = "delay_available_at")
    private Long delayAvailableAt;

    @Lob
    @Column(name = "pids_create_channel", columnDefinition = "MEDIUMTEXT")
    private String pidsCreateChannel;

    @Lob
    @Column(name = "cchannel_rsources", columnDefinition = "MEDIUMTEXT")
    private String cchannelResources;

    @Column(name = "updated")
    private Timestamp updated;

    @Column(name = "compatible")
    @ColumnDefault("false")
    private Boolean compatible;

    @Column(name = "audio_codec")
    private String audioCodec;

    @Column(name = "video_codec")
    private String videoCodec;

    @Column(name = "resolution")
    private Integer resolution;

    @Column(name = "ondemand_check")
    private Long onDemandCheck;

}