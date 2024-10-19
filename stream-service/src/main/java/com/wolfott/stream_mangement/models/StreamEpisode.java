package com.wolfott.stream_mangement.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "streams_episodes")
public class StreamEpisode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stream_episode_seq")
    @SequenceGenerator(name = "stream_episode_seq", sequenceName = "stream_episode_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "season_num")
    private Integer seasonNum;

    @Column(name = "episode_num")
    private Integer episodeNum;

//    @Column(name = "series_id")
//    private Integer seriesId;

//    @Column(name = "stream_id")
//    private Integer streamId;

    @ColumnDefault("false")
    @Column(name = "is_active")
    private Boolean active;

    @ManyToOne()
    @JoinColumn(name = "series_id")
    private StreamSeries serie;

    @ManyToOne()
    @JoinColumn(name = "stream_id")
    private Stream stream;

}
