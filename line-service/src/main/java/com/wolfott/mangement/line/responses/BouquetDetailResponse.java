package com.wolfott.mangement.line.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BouquetDetailResponse {
    private Long id;
    private String bouquetName;
//    private String bouquetChannels;
    private List<Integer> streams;
//    private String bouquetMovies;
    private List<Integer> movies;
//    private String bouquetRadios;
    private List<Integer> stations;
//    private String bouquetSeries;
    private List<Integer> series;
    private Integer bouquetOrder;
}
