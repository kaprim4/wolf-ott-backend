package com.wolfott.mangement.user.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageDetailResponse {
    private String id;
    private String packageName;
    private Boolean isAddon;
    private Boolean isTrial;
    private Boolean isOfficial;
    private Float trialCredits;
    private Float officialCredits;
    private Integer trialDuration;
    private String trialDurationIn;
    private Integer officialDuration;
    private String officialDurationIn;
    private String groups;
    private String bouquets;
    private String addonPackages;
    private Boolean isLine;
    private Boolean isMag;
    private Boolean isE2;
    private Boolean isRestreamer;
    private Boolean isIsplock;
    private String outputFormats;
    private Integer maxConnections;
    private Integer forceServerId;
    private String forcedCountry;
    private Boolean lockDevice;
    private Boolean checkCompatible;
}