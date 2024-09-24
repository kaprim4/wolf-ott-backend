package com.wolfott.mangement.line.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineUpdateResponse {
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
    private String bouquet;
    private String allowedOutputs;
    private Integer maxConnections;
    private Boolean isRestreamer;
    private Boolean isTrial;
    private Boolean isMag;
    private Boolean isE2;
    private Boolean isStalker;
    private Boolean isIsplock;
    private String allowedIps;
    private String allowedUa;
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
    private String lastActivityArray;
    private Timestamp updated;
}
