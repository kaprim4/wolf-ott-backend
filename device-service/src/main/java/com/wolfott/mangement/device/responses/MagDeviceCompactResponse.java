package com.wolfott.mangement.device.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagDeviceCompactResponse {
    private Long id;
    private Long userId;
    private String mac;
    private String ip;
    private String ver;
    private String lang;
    private Long cityId;
    private String deviceId;
    private Long lastActive;
}
