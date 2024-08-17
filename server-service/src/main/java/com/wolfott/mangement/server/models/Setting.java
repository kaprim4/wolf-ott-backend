package com.wolfott.mangement.server.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "settings")
public class Setting {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;

    @Column(name = "server_name")
    @Lob
    private String serverName;

    @Column(name = "default_timezone", columnDefinition = "VARCHAR(255) DEFAULT 'Europe/London'")
    private String defaultTimezone;

    @Column(name = "allowed_stb_types")
    @Lob
    private List<String> allowedStbTypes;

    @Column(name = "client_prebuffer", columnDefinition = "INT DEFAULT 30")
    private int clientPrebuffer;

    @Column(name = "split_clients", columnDefinition = "VARCHAR(255) DEFAULT 'equal'")
    private String splitClients;

    @Column(name = "stream_max_analyze", columnDefinition = "INT DEFAULT 5000000")
    private int streamMaxAnalyze;

    @Column(name = "show_not_on_air_video", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean showNotOnAirVideo;

    @Column(name = "not_on_air_video_path")
    @Lob
    private String notOnAirVideoPath;

    @Column(name = "show_banned_video", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean showBannedVideo;

    @Column(name = "banned_video_path")
    @Lob
    private String bannedVideoPath;

    // #TODO: Add the remaining fields following the same pattern
}
