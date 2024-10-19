package com.wolfott.mangement.device.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagDeviceDetailResponse {
    private Long id;
    private Long userId;
    private Integer bright;
    private Integer contrast;
    private Integer saturation;
    private String aspect;
    private String videoOut;
    private Integer volume;
    private Long playbackBufferBytes;
    private Long playbackBufferSize;
    private Integer audioOut;
    private String mac;
    private String ip;
    private String ls;
    private String ver;
    private String lang;
    private String locale;
    private Long cityId;
    private Integer hd;
    private Integer mainNotify;
    private Integer favItvOn;
    private Long nowPlayingStart;
    private Integer nowPlayingType;
    private String nowPlayingContent;
    private Long timeLastPlayTv;
    private Long timeLastPlayVideo;
    private Integer hdContent;
    private String imageVersion;
    private Long lastChangeStatus;
    private Long lastStart;
    private Long lastActive;
    private Long keepAlive;
    private Long playbackLimit;
    private Long screensaverDelay;
    private String stbType;
    private String sn;
    private Long lastWatchdog;
    private Long created;
    private String country;
    private Long plasmaSaving;
    private Long tsEnabled;
    private Long tsEnableIcon;
    private String tsPath;
    private Long tsMaxLength;
    private String tsBufferUse;
    private String tsActionOnExit;
    private String tsDelay;
    private String videoClock;
    private Integer rtspType;
    private Integer rtspFlags;
    private String stbLang;
    private Long displayMenuAfterLoading;
    private Long recordMaxLength;
    private Long plasmaSavingTimeout;
    private Long nowPlayingLinkId;
    private Long nowPlayingStreamerId;
    private String deviceId;
    private String deviceId2;
    private String hwVersion;
    private String parentPassword;
    private Long spdifMode;
    private String showAfterLoading;
    private Long playInPreviewByOk;
    private Long hdmiEventReaction;
    private String magPlayer;
    private String playInPreviewOnlyByOk;
    private Integer watchdogTimeout;
    private String favChannels;
    private String tvArchiveContinued;
    private String tvChannelDefaultAspect;
    private Integer lastItvId;
    private String units;
    private String token;
    private Short lockDevice;
    private Short themeType;
    private Short macFilter;
}
