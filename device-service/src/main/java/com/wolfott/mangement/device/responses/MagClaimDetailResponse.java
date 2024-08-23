package com.wolfott.mangement.device.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagClaimDetailResponse {
    private String id;
    private Integer magId;
    private Integer streamId;
    private String realType;
    private Date date;
}
