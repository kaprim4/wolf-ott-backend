package com.wolfott.mangement.line.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Entity
@Table(name = "lines_activity")
public class LineActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_id_seq")
    @SequenceGenerator(name = "activity_id_seq", sequenceName = "activity_id_seq", allocationSize = 1)
    @Column(name = "activity_id")
    private Long activityId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "stream_id")
    private Long streamId;

    @Column(name = "server_id")
    private Long serverId;

    @Column(name = "proxy_id")
    private Long proxyId;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "user_ip")
    private String userIp;

    @Column(name = "container")
    private String container;

    @Column(name = "date_start")
    private Long dateStart;

    @Column(name = "date_end")
    private Long dateEnd;

    @Column(name = "geoip_country_code")
    private String geoipCountryCode;

    @Column(name = "isp")
    private String isp;

    @Column(name = "external_device")
    private String externalDevice;

    @Column(name = "divergence")
    private Float divergence;

    @Column(name = "hmac_id")
    private Integer hmacId;

    @Column(name = "hmac_identifier")
    private String hmacIdentifier;

    // Default value for Boolean fields is false
    @Column(name = "active", columnDefinition = "boolean default false")
    @ColumnDefault("false")
    private Boolean active;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
