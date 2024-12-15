package com.wolfott.mangement.line.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "streams")
public class Stream {

    @Id
    @Column(name = "id", columnDefinition = "int")
    private Long id;

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
}
