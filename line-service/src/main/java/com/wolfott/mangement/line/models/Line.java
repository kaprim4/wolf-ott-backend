package com.wolfott.mangement.line.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "lines")
public class Line {

    @Id
    @GeneratedValue(generator = "line_id_seq")
    @GenericGenerator(
            name = "line_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "line_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "last_ip")
    private String lastIp;

    @Column(name = "exp_date")
    private Long expDate;

    @Column(name = "admin_enabled")
    @ColumnDefault("1")
    private Integer adminEnabled;

    @Column(name = "enabled")
    @ColumnDefault("1")
    private Integer enabled;

    @Lob
    @Column(name = "admin_notes")
    private String adminNotes;

    @Lob
    @Column(name = "reseller_notes")
    private String resellerNotes;

    @Lob
    @Column(name = "bouquet")
    private String bouquet;

    @Lob
    @Column(name = "allowed_outputs")
    private String allowedOutputs;

    @Column(name = "max_connections")
    @ColumnDefault("1")
    private Integer maxConnections;

    @Column(name = "is_restreamer")
    @ColumnDefault("0")
    private Boolean isRestreamer;

    @Column(name = "is_trial")
    @ColumnDefault("0")
    private Boolean isTrial;

    @Column(name = "is_mag")
    @ColumnDefault("0")
    private Boolean isMag;

    @Column(name = "is_e2")
    @ColumnDefault("0")
    private Boolean isE2;

    @Column(name = "is_stalker")
    @ColumnDefault("0")
    private Boolean isStalker;

    @Column(name = "is_isplock")
    @ColumnDefault("0")
    private Boolean isIsplock;

    @Lob
    @Column(name = "allowed_ips")
    private String allowedIps;

    @Lob
    @Column(name = "allowed_ua")
    private String allowedUa;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "pair_id")
    private Long pairId;

    @Column(name = "force_server_id")
    @ColumnDefault("0")
    private Integer forceServerId;

    @Column(name = "as_number")
    private String asNumber;

    @Lob
    @Column(name = "isp_desc")
    private String ispDesc;

    @Column(name = "forced_country")
    private String forcedCountry;

    @Column(name = "bypass_ua")
    @ColumnDefault("0")
    private Boolean bypassUa;

    @Lob
    @Column(name = "play_token")
    private String playToken;

    @Column(name = "last_expiration_video")
    private Long lastExpirationVideo;

    @Column(name = "package_id")
    private Long packageId;

    @Column(name = "access_token")
    private String accessToken;

    @Lob
    @Column(name = "contact")
    private String contact;

    @Column(name = "last_activity")
    private Long lastActivity;

    @Lob
    @Column(name = "last_activity_array")
    private String lastActivityArray;

    @Column(name = "updated", columnDefinition = "timestamp")
    @ColumnDefault("current_timestamp()")
    private Timestamp updated;
}
