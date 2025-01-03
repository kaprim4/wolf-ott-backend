package com.wolfott.mangement.line.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BouquetCreateResponse {
    private Long id;
    private String bouquetName;
    private String bouquetChannels;
    private String bouquetMovies;
    private String bouquetRadios;
    private String bouquetSeries;
    private Integer bouquetOrder;
}
