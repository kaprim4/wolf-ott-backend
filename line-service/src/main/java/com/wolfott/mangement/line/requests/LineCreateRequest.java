package com.wolfott.mangement.line.requests;

import com.wolfott.mangement.line.models.Preset;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineCreateRequest {
    private Long memberId;
    private String username;
    private String password;
    private String lastIp;
    private Long expDate;
    private Boolean adminEnabled;
    private Boolean enabled = true;
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

    private Boolean useVPN;

    private Boolean usePreset;
    private Long presetId;
}
