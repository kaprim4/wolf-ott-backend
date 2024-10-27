package com.wolfott.mangement.line.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "`lines`")
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int")
    private Long id;

//    @Column(name = "member_id", columnDefinition = "int")
    @Transient
    private Long memberId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "last_ip")
    private String lastIp;

    @Column(name = "exp_date", columnDefinition = "int")
    private Integer expDate;

    @Column(name = "admin_enabled")
    @ColumnDefault("1")
    private Integer adminEnabled;

    @Column(name = "enabled")
    @ColumnDefault("1")
    private Integer enabled;

    @Lob
    @Column(name = "admin_notes", columnDefinition = "mediumtext")
    private String adminNotes;

    @Lob
    @Column(name = "reseller_notes", columnDefinition = "mediumtext")
    private String resellerNotes;

    @Lob
    @Column(name = "bouquet", columnDefinition = "mediumtext")
    private String bouquet;

    @Lob
    @Column(name = "allowed_outputs", columnDefinition = "mediumtext")
    private String allowedOutputs;

    @Column(name = "max_connections")
    @ColumnDefault("1")
    private Integer maxConnections;

    @Column(name = "is_restreamer", columnDefinition = "tinyint")
    @ColumnDefault("0")
    private Boolean isRestreamer;

    @Column(name = "is_trial", columnDefinition = "tinyint")
    @ColumnDefault("0")
    private Boolean isTrial;

    @Column(name = "is_mag", columnDefinition = "tinyint")
    @ColumnDefault("0")
    private Boolean isMag;

    @Column(name = "is_e2", columnDefinition = "tinyint")
    @ColumnDefault("0")
    private Boolean isE2;

    @Column(name = "is_stalker", columnDefinition = "tinyint")
    @ColumnDefault("0")
    private Boolean isStalker;

    @Column(name = "is_isplock", columnDefinition = "tinyint")
    @ColumnDefault("0")
    private Boolean isIsplock;

    @Lob
    @Column(name = "allowed_ips", columnDefinition = "mediumtext")
    private String allowedIps;

    @Lob
    @Column(name = "allowed_ua", columnDefinition = "mediumtext")
    private String allowedUa;

    @Column(name = "created_at", columnDefinition = "int")
    private Long createdAt;

    @Column(name = "pair_id", columnDefinition = "int")
    private Long pairId;

    @Column(name = "force_server_id")
    @ColumnDefault("0")
    private Integer forceServerId;

    @Column(name = "as_number")
    private String asNumber;

    @Lob
    @Column(name = "isp_desc", columnDefinition = "mediumtext")
    private String ispDesc;

    @Column(name = "forced_country")
    private String forcedCountry;

    @Column(name = "bypass_ua", columnDefinition = "tinyint")
    @ColumnDefault("0")
    private Boolean bypassUa;

    @Lob
    @Column(name = "play_token", columnDefinition = "mediumtext")
    private String playToken;

    @Column(name = "last_expiration_video", columnDefinition = "int")
    private Long lastExpirationVideo;

    @Column(name = "package_id", columnDefinition = "int")
    private Long packageId;

    @Column(name = "access_token")
    private String accessToken;

    @Lob
    @Column(name = "contact", columnDefinition = "mediumtext")
    private String contact;

    @Column(name = "last_activity", columnDefinition = "int")
    private Long lastActivity;

    @Lob
    @Column(name = "last_activity_array", columnDefinition = "mediumtext")
    private String lastActivityArray;

//    @Column(name = "updated", columnDefinition = "timestamp")
//    @ColumnDefault("current_timestamp()")
//    private Timestamp updated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id") // , insertable = false, updatable = false
//    @Transient
    private User member;
}
