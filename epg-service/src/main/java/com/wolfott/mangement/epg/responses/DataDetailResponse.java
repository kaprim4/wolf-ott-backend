package com.wolfott.mangement.epg.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataDetailResponse {
    private String id;
    private String title;
    private String lang;
    private Integer start;
    private Integer end;
    private String description;
    private String channelId;
}
