package com.wolfott.mangement.line.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineCompactResponse {

    private Long id;
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
    private String allowedIps;
    private String allowedUa;
    private Long createdAt;
    private Long pairId;
    private Integer forceServerId;
    private String asNumber;
    private String ispDesc;
    private String forcedCountry;
    private Long packageId;
    private String contact;
    private Timestamp updated;
}
