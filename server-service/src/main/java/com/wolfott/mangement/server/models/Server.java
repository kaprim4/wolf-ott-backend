package com.wolfott.mangement.server.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Data
@Table(name = "servers")
public class Server {

    @Id
    @GeneratedValue(generator = "server_id_seq")
    @GenericGenerator(
            name = "server_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "server_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "server_type")
    @ColumnDefault("0")
    private Integer serverType;

    @Column(name = "xui_version")
    private String xuiVersion;

    @Column(name = "server_name")
    private String serverName;

    @Lob
    @Column(name = "domain_name")
    private String domainName;

    @Column(name = "server_ip")
    private String serverIp;

    @Column(name = "private_ip")
    private String privateIp;

    @Column(name = "is_main")
    @ColumnDefault("0")
    private Boolean isMain;

    @Column(name = "enabled")
    @ColumnDefault("1")
    private Boolean enabled;

    @Lob
    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "http_broadcast_port")
    private Integer httpBroadcastPort;

    @Column(name = "https_broadcast_port")
    private Integer httpsBroadcastPort;

    @Lob
    @Column(name = "http_ports_add")
    private String httpPortsAdd;

    @Lob
    @Column(name = "https_ports_add")
    private String httpsPortsAdd;

    @Column(name = "total_clients")
    private Integer totalClients;

    @Column(name = "network_interface")
    private String networkInterface;

    @Column(name = "status")
    @ColumnDefault("-1")
    private Integer status;

    @Column(name = "enable_geoip")
    @ColumnDefault("0")
    private Integer enableGeoip;

    @Lob
    @Column(name = "geoip_countries")
    private String geoipCountries;

    @Column(name = "last_check_ago")
    private Integer lastCheckAgo;

    @Lob
    @Column(name = "server_hardware")
    private String serverHardware;

    @Column(name = "total_services")
    private Integer totalServices;

    @Column(name = "persistent_connections")
    @ColumnDefault("0")
    private Boolean persistentConnections;

    @Column(name = "rtmp_port")
    private Integer rtmpPort;

    @Column(name = "geoip_type")
    private String geoipType;

    @Lob
    @Column(name = "isp_names")
    private String ispNames;

    @Column(name = "isp_type")
    private String ispType;

    @Column(name = "enable_isp")
    @ColumnDefault("0")
    private Boolean enableIsp;

    @Column(name = "network_guaranteed_speed")
    private Integer networkGuaranteedSpeed;

    @Column(name = "timeshift_only")
    @ColumnDefault("0")
    private Boolean timeshiftOnly;

    @Lob
    @Column(name = "whitelist_ips")
    private String whitelistIps;

    @Lob
    @Column(name = "watchdog_data")
    private String watchdogData;

    @Lob
    @Column(name = "video_devices")
    private String videoDevices;

    @Lob
    @Column(name = "audio_devices")
    private String audioDevices;

    @Lob
    @Column(name = "gpu_info")
    private String gpuInfo;

    @Lob
    @Column(name = "interfaces")
    private String interfaces;

    @Column(name = "random_ip")
    @ColumnDefault("0")
    private Boolean randomIp;

    @Column(name = "enable_proxy")
    @ColumnDefault("0")
    private Boolean enableProxy;

    @Column(name = "enable_https")
    @ColumnDefault("0")
    private Boolean enableHttps;

    @Column(name = "certbot_renew")
    @ColumnDefault("0")
    private Boolean certbotRenew;

    @Lob
    @Column(name = "certbot_ssl")
    private String certbotSsl;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "use_disk")
    @ColumnDefault("0")
    private Boolean useDisk;

    @Column(name = "last_status")
    @ColumnDefault("1")
    private Integer lastStatus;

    @Column(name = "time_offset")
    private Integer timeOffset;

    @Column(name = "ping")
    private Integer ping;

    @Column(name = "requests_per_second")
    private Integer requestsPerSecond;

    @Column(name = "xui_revision")
    private Integer xuiRevision;

    @Column(name = "php_version")
    @ColumnDefault("74")
    private Integer phpVersion;

    @Lob
    @Column(name = "php_pids")
    private List<Long> phpPids;

    @Column(name = "connections")
    private Integer connections;

    @Column(name = "users")
    private Integer users;

    @Column(name = "remote_status")
    @ColumnDefault("1")
    private Boolean remoteStatus;

    @Lob
    @Column(name = "governors")
    private String governors;

    @Column(name = "governor")
    private String governor;

    @Lob
    @Column(name = "sysctl")
    private String sysctl;

    @Column(name = "order")
    private Integer order;

    @Column(name = "enable_gzip")
    @ColumnDefault("0")
    private Boolean enableGzip;

    @Column(name = "limit_requests")
    private Integer limitRequests;

    @Column(name = "limit_burst")
    private Integer limitBurst;

}