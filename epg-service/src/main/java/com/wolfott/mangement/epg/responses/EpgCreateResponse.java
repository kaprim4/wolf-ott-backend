package com.wolfott.mangement.epg.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EpgCreateResponse {
    private Long id;
    private String epgName;
    private String epgFile;
    private Integer lastUpdated;
    private Integer daysKeep;
    private String data;
    private Integer offset;
}
