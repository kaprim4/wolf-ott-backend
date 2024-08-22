package com.wolfott.mangement.device.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enigma2ActionCompactResponse {
    private String id;
    private String type;
    private String key;
    private String command;
}
