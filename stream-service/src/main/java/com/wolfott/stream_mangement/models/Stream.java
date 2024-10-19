package com.wolfott.stream_mangement.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ColumnTransformer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "streams")
public class Stream {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stream_seq")
//    @SequenceGenerator(name = "stream_seq", sequenceName = "stream_seq", allocationSize = 1)
    @Column(name = "id", columnDefinition = "int")
    private Long id;

//    @Column(name = "type")
//    private Integer type;

    @Column(name = "category_id", columnDefinition = "mediumtext")
//    @ColumnTransformer(read = "CAST(AES_DECRYPT(category_id, 'key') AS CHAR(255))", write = "AES_ENCRYPT(?, 'key')")
    private String categoryId;

    @Column(name = "stream_display_name", columnDefinition = "mediumtext")
    private String streamDisplayName;

    @Column(name = "stream_source")
    private String streamSource;

    @Column(name = "stream_icon")
    private String streamIcon;

    @Column(name = "notes")
    private String notes;

    @Column(name = "enable_transcode", columnDefinition = "tinyint")
    @ColumnDefault("false")
    private Boolean enableTranscode;

    @Lob
    @Column(name = "transcode_attributes", columnDefinition = "mediumtext")
    private String transcodeAttributes;

    @Lob
    @Column(name = "custom_ffmpeg", columnDefinition = "mediumtext")
    private String customFfmpeg;

    @Lob
    @Column(name = "movie_properties", columnDefinition = "mediumtext")
    private String movieProperties;

    @Lob
    @Column(name = "movie_subtitles", columnDefinition = "mediumtext")
    private String movieSubtitles;

    @Column(name = "read_native", columnDefinition = "tinyint")
    @ColumnDefault("true")
    private Boolean readNative;

    @Column(name = "target_container", columnDefinition = "mediumtext")
    private String targetContainer;

    @Column(name = "stream_all", columnDefinition = "tinyint")
    @ColumnDefault("false")
    private Boolean streamAll;

    @Column(name = "remove_subtitles", columnDefinition = "tinyint")
    @ColumnDefault("false")
    private Boolean removeSubtitles;

    @Column(name = "custom_sid", columnDefinition = "mediumtext")
    private String customSid;

    @Column(name = "epg_api", columnDefinition = "int")
    @ColumnDefault("false")
    private Boolean epgApi;

    @Column(name = "epg_id")
    private Integer epgId;

    @Column(name = "channel_id", columnDefinition = "mediumtext")
    private String channelId;

    @Column(name = "epg_lang", columnDefinition = "mediumtext")
    private String epgLang;

    @Column(name = "order")
    private Integer order;

    @Lob
    @Column(name = "auto_restart", columnDefinition = "mediumtext")
    private String autoRestart;

    @Column(name = "transcode_profile_id")
    private Integer transcodeProfileId;

    @Column(name = "gen_timestamps", columnDefinition = "tinyint")
    @ColumnDefault("true")
    private Boolean genTimestamps;

    @Column(name = "added")
    private Integer added;

    @Column(name = "series_no")
    private Integer seriesNo;

    @Column(name = "direct_source", columnDefinition = "tinyint")
    @ColumnDefault("false")
    private Boolean directSource;

    @Column(name = "tv_archive_duration")
    private Integer tvArchiveDuration;

    @Column(name = "tv_archive_server_id")
    private Integer tvArchiveServerId;

    @Column(name = "tv_archive_pid")
    private Integer tvArchivePid;

    @Column(name = "vframes_server_id")
    private Integer vframesServerId;

    @Column(name = "vframes_pid")
    private Integer vframesPid;

    @Column(name = "movie_symlink", columnDefinition = "tinyint")
    @ColumnDefault("false")
    private Boolean movieSymlink;

    @Column(name = "rtmp_output", columnDefinition = "tinyint")
    @ColumnDefault("false")
    private Boolean rtmpOutput;

    @Column(name = "allow_record", columnDefinition = "tinyint")
    @ColumnDefault("false")
    private Boolean allowRecord;

    @Column(name = "probesize_ondemand")
    private Integer probesizeOndemand;

    @Lob
    @Column(name = "custom_map", columnDefinition = "mediumtext")
    private String customMap;

    @Lob
    @Column(name = "external_push", columnDefinition = "mediumtext")
    private String externalPush;

    @Column(name = "delay_minutes")
    private Integer delayMinutes;

    @Column(name = "tmdb_language", columnDefinition = "mediumtext")
    private String tmdbLanguage;

    @Column(name = "llod", columnDefinition = "tinyint")
    @ColumnDefault("false")
    private Boolean llod;

    @Column(name = "year")
    private Integer year;

    @Column(name = "rating")
    @ColumnDefault("0")
    private Float rating;

    @Column(name = "plex_uuid", columnDefinition = "mediumtext")
    private String plexUuid;

    @Column(name = "uuid", columnDefinition = "mediumtext")
    private String uuid;

    @Column(name = "epg_offset")
    private Integer epgOffset;

    @Column(name = "updated")
    @ColumnDefault("current_timestamp()")
    private Date updated;

    @Lob
    @Column(name = "similar", columnDefinition = "mediumtext")
    private String similar;

    @Column(name = "tmdb_id")
    private Integer tmdbId;

    @Lob
    @Column(name = "adaptive_link", columnDefinition = "mediumtext")
    private String adaptiveLink;

    @Column(name = "title_sync", columnDefinition = "mediumtext")
    private String titleSync;

    @Column(name = "fps_restart", columnDefinition = "tinyint")
    @ColumnDefault("false")
    private Boolean fpsRestart;

    @Column(name = "fps_threshold")
    private Integer fpsThreshold;

    @Column(name = "direct_proxy", columnDefinition = "tinyint")
    @ColumnDefault("false")
    private Boolean directProxy;

    @OneToMany(mappedBy = "stream", fetch = FetchType.LAZY)
    private List<StreamEpisode> episodes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "type", columnDefinition = "int")
    private StreamType type;

}
