package com.wolfott.mangement.line.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BouquetUpdateResponse {
    private Long id;
    private String bouquetName;
    private String bouquetChannels;
    private String bouquetMovies;
    private String bouquetRadios;
    private String bouquetSeries;
    private Integer bouquetOrder;
}
