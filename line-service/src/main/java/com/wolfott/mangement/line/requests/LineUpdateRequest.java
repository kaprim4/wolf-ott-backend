package com.wolfott.mangement.line.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineUpdateRequest {
    private Long id;
    private Long memberId;
    private String username;
    private String password;
    private String lastIp;
    private Long expDate;
    private Integer adminEnabled;
    private Integer enabled;
    private String adminNotes;
    private String resellerNotes;
    private List<Integer> bouquets;
    private List<Integer> allowedOutputs;
    private Integer maxConnections;
    private Boolean isRestreamer;
    private Boolean isTrial;
    private Boolean isMag;
    private Boolean isE2;
    private Boolean isStalker;
    private Boolean isIsplock;
    private List<String> allowedIps;
    private List<String> allowedUa;
    private Long createdAt;
    private Long pairId;
    private Integer forceServerId;
    private String asNumber;
    private String ispDesc;
    private String forcedCountry;
    private Boolean bypassUa;
    private String playToken;
    private Long lastExpirationVideo;
    private Long packageId;
    private String accessToken;
    private String contact;
    private Long lastActivity;
    private Map<String, Object> lastActivityArray;
    private Timestamp updated;

    private Boolean usePreset;
    private Long presetId;
}
