package com.wolfott.mangement.line.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "lines_live")
public class LineLive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "pid")
    private Long pid;

    @Column(name = "active_pid")
    private Long activePid;

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

    @Column(name = "hls_last_read")
    private Long hlsLastRead;

    @Column(name = "hls_end")
    @ColumnDefault("0")
    private Boolean hlsEnd;

    @Column(name = "fingerprinting")
    @ColumnDefault("0")
    private Boolean fingerprinting;

    @Column(name = "hmac_id")
    private Integer hmacId;

    @Column(name = "hmac_identifier")
    private String hmacIdentifier;

    @Column(name = "uuid")
    private String uuid;

}
