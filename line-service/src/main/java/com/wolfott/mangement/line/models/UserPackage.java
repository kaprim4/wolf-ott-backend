package com.wolfott.mangement.line.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Table(name = "users_packages")
public class UserPackage {

    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "package_name")
    private String packageName;

    @Column(name = "is_addon")
    @ColumnDefault("false")
    private Boolean isAddon;

    @Column(name = "is_trial")
    @ColumnDefault("false")
    private Boolean isTrial;

    @Column(name = "is_official")
    @ColumnDefault("false")
    private Boolean isOfficial;

    @Column(name = "trial_credits")
    private Float trialCredits;

    @Column(name = "official_credits")
    private Float officialCredits;

    @Column(name = "trial_duration")
    private Integer trialDuration;

    @Column(name = "trial_duration_in")
    private String trialDurationIn;

    @Column(name = "official_duration")
    private Integer officialDuration;

    @Column(name = "official_duration_in")
    private String officialDurationIn;

    @Lob
    @Column(name = "groups", columnDefinition = "LONGTEXT")
    private String groups;

    @Lob
    @Column(name = "bouquets", columnDefinition = "LONGTEXT")
    private String bouquets;

    @Lob
    @Column(name = "addon_packages", columnDefinition = "LONGTEXT")
    private String addonPackages;

    @Column(name = "is_line")
    @ColumnDefault("false")
    private Boolean isLine;

    @Column(name = "is_mag")
    @ColumnDefault("false")
    private Boolean isMag;

    @Column(name = "is_e2")
    @ColumnDefault("false")
    private Boolean isE2;

    @Column(name = "is_restreamer")
    @ColumnDefault("false")
    private Boolean isRestreamer;

    @Column(name = "is_isplock")
    @ColumnDefault("false")
    private Boolean isIsplock;

    @Lob
    @Column(name = "output_formats", columnDefinition = "LONGTEXT")
    private String outputFormats;

    @Column(name = "max_connections")
    private Integer maxConnections;

    @Column(name = "force_server_id")
    private Integer forceServerId;

    @Column(name = "forced_country")
    private String forcedCountry;

    @Column(name = "lock_device")
    @ColumnDefault("true")
    private Boolean lockDevice;

    @Column(name = "check_compatible")
    @ColumnDefault("true")
    private Boolean checkCompatible;

}