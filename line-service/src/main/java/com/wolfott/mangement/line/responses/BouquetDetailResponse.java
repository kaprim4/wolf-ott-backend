package com.wolfott.mangement.line.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BouquetDetailResponse {
    private Long id;
    private String bouquetName;
    private List<Integer> streams;
    private List<Integer> movies;
    private List<Integer> stations;
    private List<Integer> series;
    private Integer bouquetOrder;
}
