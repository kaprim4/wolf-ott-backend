package com.wolfott.mangement.epg.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EpgCompactResponse {
    private Long id;
    private String epgName;
    private Integer lastUpdated;
    private Integer daysKeep;
}
