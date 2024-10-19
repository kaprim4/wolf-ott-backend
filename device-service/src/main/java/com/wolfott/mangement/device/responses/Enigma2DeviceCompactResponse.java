package com.wolfott.mangement.device.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enigma2DeviceCompactResponse {
    private Long id;
    private String mac;
    private String ip;
    private String owner;
    private Boolean status;
    private Boolean online;
    private Boolean trial;
    private Date expiration;

}
