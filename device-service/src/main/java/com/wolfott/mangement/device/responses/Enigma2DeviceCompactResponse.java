package com.wolfott.mangement.device.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enigma2DeviceCompactResponse {
    private Long id;
    private Long userId;
    private String enigmaVersion;
    private String cpu;
    private String version;
    private String lversion;
    private Long lastUpdated;
    private Long watchdogTimeout;
    private Boolean lockDevice;
    private Boolean telnetEnable;
    private Boolean ftpEnable;
    private Boolean sshEnable;
    private String dns;
    private String originalMac;
    private Boolean rc;
}