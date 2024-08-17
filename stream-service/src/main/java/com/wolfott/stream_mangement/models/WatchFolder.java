package com.wolfott.stream_mangement.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name = "watch_folders")
public class WatchFolder {

    @Id
    @GeneratedValue(generator = "watch_folder_id_seq")
    @GenericGenerator(
            name = "watch_folder_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "watch_folder_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "directory", length = 2048)
    private String directory;

    @Column(name = "rclone_dir", length = 2048)
    private String rcloneDir;

    @Column(name = "server_id")
    private Integer serverId;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "bouquets", length = 4096)
    private String bouquets;

    @Column(name = "last_run")
    private Integer lastRun;

    @Column(name = "active")
    @ColumnDefault("1")
    private Boolean active;

    @Column(name = "disable_tmdb")
    @ColumnDefault("0")
    private Boolean disableTmdb;

    @Column(name = "ignore_no_match")
    @ColumnDefault("0")
    private Boolean ignoreNoMatch;

    @Column(name = "auto_subtitles")
    @ColumnDefault("0")
    private Boolean autoSubtitles;

    @Column(name = "fb_bouquets", length = 4096)
    private String fbBouquets;

    @Column(name = "fb_category_id")
    private Integer fbCategoryId;

    @Column(name = "allowed_extensions", length = 4096)
    private String allowedExtensions;

    @Column(name = "language")
    private String language;

    @Column(name = "read_native")
    @ColumnDefault("0")
    private Boolean readNative;

    @Column(name = "movie_symlink")
    @ColumnDefault("0")
    private Boolean movieSymlink;

    @Column(name = "auto_encode")
    @ColumnDefault("1")
    private Boolean autoEncode;

    @Column(name = "ffprobe_input")
    @ColumnDefault("1")
    private Boolean ffprobeInput;

    @Column(name = "transcode_profile_id")
    private Integer transcodeProfileId;

    @Column(name = "auto_upgrade")
    @ColumnDefault("0")
    private Boolean autoUpgrade;

    @Column(name = "fallback_title")
    @ColumnDefault("0")
    private Boolean fallbackTitle;

    @Column(name = "plex_ip", length = 128)
    private String plexIp;

    @Column(name = "plex_port")
    private Integer plexPort;

    @Column(name = "plex_username", length = 256)
    private String plexUsername;

    @Column(name = "plex_password", length = 256)
    private String plexPassword;

    @Lob
    @Column(name = "plex_libraries")
    private String plexLibraries;

    @Column(name = "scan_missing")
    @ColumnDefault("0")
    private Boolean scanMissing;

    @Column(name = "extract_metadata")
    @ColumnDefault("0")
    private Boolean extractMetadata;

    @Column(name = "store_categories")
    @ColumnDefault("0")
    private Boolean storeCategories;

    @Column(name = "duplicate_tmdb")
    @ColumnDefault("0")
    private Boolean duplicateTmdb;

    @Column(name = "check_tmdb")
    @ColumnDefault("1")
    private Boolean checkTmdb;

    @Column(name = "remove_subtitles")
    @ColumnDefault("0")
    private Boolean removeSubtitles;

    @Column(name = "target_container", length = 64)
    private String targetContainer;

    @Column(name = "server_add", length = 512)
    private String serverAdd;

    @Column(name = "direct_proxy")
    @ColumnDefault("0")
    private Boolean directProxy;

    @Column(name = "plex_token", length = 512)
    private String plexToken;

}