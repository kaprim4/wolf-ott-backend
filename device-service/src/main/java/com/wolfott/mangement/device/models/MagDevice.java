package com.wolfott.mangement.device.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Table(name = "mag_devices")
public class MagDevice {

    @Id
    @GeneratedValue(generator = "mag_id_seq")
    @GenericGenerator(
            name = "mag_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "mag_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "mag_id")
    private Long magId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "bright")
    @ColumnDefault("200")
    private Integer bright;

    @Column(name = "contrast")
    @ColumnDefault("127")
    private Integer contrast;

    @Column(name = "saturation")
    @ColumnDefault("127")
    private Integer saturation;

    @Lob
    @Column(name = "aspect")
    private String aspect;

    @Column(name = "video_out")
    @ColumnDefault("'rca'")
    private String videoOut;

    @Column(name = "volume")
    @ColumnDefault("50")
    private Integer volume;

    @Column(name = "playback_buffer_bytes")
    @ColumnDefault("0")
    private Long playbackBufferBytes;

    @Column(name = "playback_buffer_size")
    @ColumnDefault("0")
    private Long playbackBufferSize;

    @Column(name = "audio_out")
    @ColumnDefault("1")
    private Integer audioOut;

    @Column(name = "mac")
    private String mac;

    @Column(name = "ip")
    private String ip;

    @Column(name = "ls")
    private String ls;

    @Column(name = "ver")
    private String ver;

    @Column(name = "lang")
    private String lang;

    @Column(name = "locale")
    @ColumnDefault("'en_GB.utf8'")
    private String locale;

    @Column(name = "city_id")
    @ColumnDefault("0")
    private Long cityId;

    @Column(name = "hd")
    @ColumnDefault("1")
    private Integer hd;

    @Column(name = "main_notify")
    @ColumnDefault("1")
    private Integer mainNotify;

    @Column(name = "fav_itv_on")
    @ColumnDefault("0")
    private Integer favItvOn;

    @Column(name = "now_playing_start")
    private Long nowPlayingStart;

    @Column(name = "now_playing_type")
    @ColumnDefault("0")
    private Integer nowPlayingType;

    @Column(name = "now_playing_content")
    private String nowPlayingContent;

    @Column(name = "time_last_play_tv")
    private Long timeLastPlayTv;

    @Column(name = "time_last_play_video")
    private Long timeLastPlayVideo;

    @Column(name = "hd_content")
    @ColumnDefault("1")
    private Integer hdContent;

    @Column(name = "image_version")
    private String imageVersion;

    @Column(name = "last_change_status")
    private Long lastChangeStatus;

    @Column(name = "last_start")
    private Long lastStart;

    @Column(name = "last_active")
    private Long lastActive;

    @Column(name = "keep_alive")
    private Long keepAlive;

    @Column(name = "playback_limit")
    @ColumnDefault("3")
    private Long playbackLimit;

    @Column(name = "screensaver_delay")
    @ColumnDefault("10")
    private Long screensaverDelay;

    @Column(name = "stb_type")
    private String stbType;

    @Column(name = "sn")
    private String sn;

    @Column(name = "last_watchdog")
    private Long lastWatchdog;

    @Column(name = "created")
    private Long created;

    @Column(name = "country")
    private String country;

    @Column(name = "plasma_saving")
    @ColumnDefault("0")
    private Long plasmaSaving;

    @Column(name = "ts_enabled")
    @ColumnDefault("0")
    private Long tsEnabled;

    @Column(name = "ts_enable_icon")
    @ColumnDefault("1")
    private Long tsEnableIcon;

    @Column(name = "ts_path")
    private String tsPath;

    @Column(name = "ts_max_length")
    @ColumnDefault("3600")
    private Long tsMaxLength;

    @Column(name = "ts_buffer_use")
    @ColumnDefault("'cyclic'")
    private String tsBufferUse;

    @Column(name = "ts_action_on_exit")
    @ColumnDefault("'no_save'")
    private String tsActionOnExit;

    @Column(name = "ts_delay")
    @ColumnDefault("'on_pause'")
    private String tsDelay;

    @Column(name = "video_clock")
    @ColumnDefault("'Off'")
    private String videoClock;

    @Column(name = "rtsp_type")
    @ColumnDefault("4")
    private Integer rtspType;

    @Column(name = "rtsp_flags")
    @ColumnDefault("0")
    private Integer rtspFlags;

    @Column(name = "stb_lang")
    @ColumnDefault("'en'")
    private String stbLang;

    @Column(name = "display_menu_after_loading")
    @ColumnDefault("1")
    private Long displayMenuAfterLoading;

    @Column(name = "record_max_length")
    @ColumnDefault("180")
    private Long recordMaxLength;

    @Column(name = "plasma_saving_timeout")
    @ColumnDefault("600")
    private Long plasmaSavingTimeout;

    @Column(name = "now_playing_link_id")
    private Long nowPlayingLinkId;

    @Column(name = "now_playing_streamer_id")
    private Long nowPlayingStreamerId;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "device_id2")
    private String deviceId2;

    @Column(name = "hw_version")
    private String hwVersion;

    @Column(name = "parent_password")
    @ColumnDefault("'0000'")
    private String parentPassword;

    @Column(name = "spdif_mode")
    @ColumnDefault("1")
    private Long spdifMode;

    @Column(name = "show_after_loading")
    @ColumnDefault("'main_menu'")
    private String showAfterLoading;

    @Column(name = "play_in_preview_by_ok")
    @ColumnDefault("1")
    private Long playInPreviewByOk;

    @Column(name = "hdmi_event_reaction")
    @ColumnDefault("1")
    private Long hdmiEventReaction;

    @Column(name = "mag_player")
    @ColumnDefault("'ffmpeg'")
    private String magPlayer;

    // #TODO: rest of columns
}

