package com.wolfott.stream_mangement.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "streams_series")
public class StreamSeries {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "series_seq")
    @SequenceGenerator(name = "series_seq", sequenceName = "series_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "category_id", columnDefinition = "LONGTEXT")
    private String categoryId;

    @Column(name = "cover")
    private String cover;

    @Column(name = "cover_big")
    private String coverBig;

    @Column(name = "genre")
    private String genre;

    @Lob
    @Column(name = "plot", columnDefinition = "MEDIUMTEXT")
    private String plot;

    @Lob
    @Column(name = "cast", columnDefinition = "MEDIUMTEXT")
    private String cast;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "director")
    private String director;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "last_modified")
    private Integer lastModified;

    @Column(name = "tmdb_id")
    private Integer tmdbId;

    @Lob
    @Column(name = "seasons", columnDefinition = "MEDIUMTEXT")
    private String seasons;

    @Column(name = "episode_run_time")
    @ColumnDefault("0")
    private Integer episodeRunTime;

    @Lob
    @Column(name = "backdrop_path", columnDefinition = "MEDIUMTEXT")
    private String backdropPath;

    @Lob
    @Column(name = "youtube_trailer", columnDefinition = "MEDIUMTEXT")
    private String youtubeTrailer;

    @Column(name = "tmdb_language")
    private String tmdbLanguage;

    @Column(name = "year")
    private Integer year;

    @Column(name = "plex_uuid")
    @ColumnDefault("''")
    private String plexUuid;

    @Lob
    @Column(name = "similar", columnDefinition = "MEDIUMTEXT")
    private String similar;

    @OneToMany(mappedBy = "serie", fetch = FetchType.LAZY)
    private List<StreamEpisode> episodes;

}

