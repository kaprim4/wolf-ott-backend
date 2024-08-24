package com.wolfott.mangement.epg.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiCompactResponse {
    private Long stationId;
    private String name;
    private String bcastLangs;
    private String type;
    private String signalType;
    private String videoType;
    private String picon;
    private Boolean eng;
}
