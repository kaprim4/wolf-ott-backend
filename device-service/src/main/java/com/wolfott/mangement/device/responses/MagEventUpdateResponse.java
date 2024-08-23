package com.wolfott.mangement.device.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagEventUpdateResponse {
    private Long id;
    private Integer status = 0;
    private Long magDeviceId;
    private String event;
    private Integer needConfirm = 0;
    private String msg;
    private Integer rebootAfterOk = 0;
    private Integer autoHideTimeout = 0;
    private Long sendTime;
    private Integer additionalServicesOn = 1;
    private Integer anec = 0;
    private Integer vclub = 0;
}
