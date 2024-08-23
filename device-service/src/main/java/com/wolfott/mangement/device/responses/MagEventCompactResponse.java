package com.wolfott.mangement.device.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagEventCompactResponse {
    private Long id;
    private Integer status = 0;
    private Long magDeviceId;
    private String event;
    private Integer needConfirm = 0;
    private String msg;
    private Long sendTime;
}
