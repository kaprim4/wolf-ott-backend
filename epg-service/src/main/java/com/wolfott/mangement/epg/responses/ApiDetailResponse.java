package com.wolfott.mangement.epg.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiDetailResponse {
    private Long stationId;
    private String altId;
    private String callSign;
    private String name;
    private String bcastLangs;
    private String type;
    private String signalType;
    private String videoType;
    private Integer affiliateId;
    private String affiliateCallSign;
    private String picon;
    private Boolean eng;
}
