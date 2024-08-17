package com.wolfott.stream_mangement.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ColumnTransformer;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "streams")
public class Stream {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stream_seq")
    @SequenceGenerator(name = "stream_seq", sequenceName = "stream_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private Integer type;

    @Column(name = "category_id")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(category_id, 'key') AS CHAR(255))", write = "AES_ENCRYPT(?, 'key')")
    private String categoryId;

    @Column(name = "stream_display_name")
    private String streamDisplayName;

    @Column(name = "stream_source")
    private String streamSource;

    @Column(name = "stream_icon")
    private String streamIcon;

    @Column(name = "notes")
    private String notes;

    @Column(name = "enable_transcode")
    @ColumnDefault("false")
    private Boolean enableTranscode;

    @Lob
    @Column(name = "transcode_attributes")
    private String transcodeAttributes;

    @Lob
    @Column(name = "custom_ffmpeg")
    private String customFfmpeg;

    @Lob
    @Column(name = "movie_properties")
    private String movieProperties;

    @Lob
    @Column(name = "movie_subtitles")
    private String movieSubtitles;

    @Column(name = "read_native")
    @ColumnDefault("true")
    private Boolean readNative;

    @Column(name = "target_container")
    private String targetContainer;

    @Column(name = "stream_all")
    @ColumnDefault("false")
    private Boolean streamAll;

    @Column(name = "remove_subtitles")
    @ColumnDefault("false")
    private Boolean removeSubtitles;

    @Column(name = "custom_sid")
    private String customSid;

    @Column(name = "epg_api")
    @ColumnDefault("false")
    private Boolean epgApi;

    @Column(name = "epg_id")
    private Integer epgId;

    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "epg_lang")
    private String epgLang;

    @Column(name = "order")
    private Integer order;

    @Lob
    @Column(name = "auto_restart")
    private String autoRestart;

    @Column(name = "transcode_profile_id")
    private Integer transcodeProfileId;

    @Column(name = "gen_timestamps")
    @ColumnDefault("true")
    private Boolean genTimestamps;

    @Column(name = "added")
    private Integer added;

    @Column(name = "series_no")
    private Integer seriesNo;

    @Column(name = "direct_source")
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

    @Column(name = "movie_symlink")
    @ColumnDefault("false")
    private Boolean movieSymlink;

    @Column(name = "rtmp_output")
    @ColumnDefault("false")
    private Boolean rtmpOutput;

    @Column(name = "allow_record")
    @ColumnDefault("false")
    private Boolean allowRecord;

    @Column(name = "probesize_ondemand")
    private Integer probesizeOndemand;

    @Lob
    @Column(name = "custom_map")
    private String customMap;

    @Lob
    @Column(name = "external_push")
    private String externalPush;

    @Column(name = "delay_minutes")
    private Integer delayMinutes;

    @Column(name = "tmdb_language")
    private String tmdbLanguage;

    @Column(name = "llod")
    @ColumnDefault("false")
    private Boolean llod;

    @Column(name = "year")
    private Integer year;

    @Column(name = "rating")
    @ColumnDefault("0")
    private Float rating;

    @Column(name = "plex_uuid")
    private String plexUuid;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "epg_offset")
    private Integer epgOffset;

    @Column(name = "updated")
    @ColumnDefault("current_timestamp()")
    private Date updated;

    @Lob
    @Column(name = "similar")
    private String similar;

    @Column(name = "tmdb_id")
    private Integer tmdbId;

    @Lob
    @Column(name = "adaptive_link")
    private String adaptiveLink;

    @Column(name = "title_sync")
    private String titleSync;

    @Column(name = "fps_restart")
    @ColumnDefault("false")
    private Boolean fpsRestart;

    @Column(name = "fps_threshold")
    private Integer fpsThreshold;

    @Column(name = "direct_proxy")
    @ColumnDefault("false")
    private Boolean directProxy;

}
