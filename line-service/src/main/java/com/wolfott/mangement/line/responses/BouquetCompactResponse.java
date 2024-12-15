package com.wolfott.mangement.line.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BouquetCompactResponse {
    private Long id;
    private String bouquetName;
    private Integer bouquetOrder;
    private Integer streams;
    private Integer movies;
    private Integer series;
    private Integer stations;
}
