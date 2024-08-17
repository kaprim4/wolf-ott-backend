package com.wolfott.mangement.device.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table(name = "enigma2_devices")
public class Enigma2Device {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_seq")
    @SequenceGenerator(name = "device_seq", sequenceName = "device_seq", allocationSize = 1)
    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "mac")
    private String mac;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "modem_mac")
    private String modemMac;

    @Column(name = "local_ip")
    private String localIp;

    @Column(name = "public_ip")
    private String publicIp;

    @Column(name = "key_auth")
    private String keyAuth;

    @Column(name = "enigma_version")
    private String enigmaVersion;

    @Column(name = "cpu")
    private String cpu;

    @Column(name = "version")
    private String version;

    @Lob
    @Column(name = "lversion", columnDefinition = "MEDIUMTEXT")
    private String lversion;

    @Column(name = "token")
    private String token;

    @Column(name = "last_updated")
    private Long lastUpdated;

    @Column(name = "watchdog_timeout")
    private Long watchdogTimeout;

    @Column(name = "lock_device")
    @ColumnDefault("false")
    private Boolean lockDevice;

    @Column(name = "telnet_enable")
    @ColumnDefault("true")
    private Boolean telnetEnable;

    @Column(name = "ftp_enable")
    @ColumnDefault("true")
    private Boolean ftpEnable;

    @Column(name = "ssh_enable")
    @ColumnDefault("true")
    private Boolean sshEnable;

    @Column(name = "dns")
    private String dns;

    @Column(name = "original_mac")
    private String originalMac;

    @Column(name = "rc")
    @ColumnDefault("true")
    private Boolean rc;

    @Column(name = "mac_filter")
    private String macFilter;

}
