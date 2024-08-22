package com.wolfott.mangement.device.requests;

public record Enigma2DeviceUpdateRequest(Long id,
                                         String mac,
                                         Long userId,
                                         String modemMac,
                                         String localIp,
                                         String publicIp,
                                         String keyAuth,
                                         String enigmaVersion,
                                         String cpu,
                                         String version,
                                         String lversion,
                                         String token,
                                         Long lastUpdated,
                                         Long watchdogTimeout,
                                         Boolean lockDevice,
                                         Boolean telnetEnable,
                                         Boolean ftpEnable,
                                         Boolean sshEnable,
                                         String dns,
                                         String originalMac,
                                         Boolean rc,
                                         String macFilter) {

}
