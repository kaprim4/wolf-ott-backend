package com.wolfott.mangement.line.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BouquetCompactResponse {
    private Long id;
    private String bouquetName;
    private Integer bouquetOrder;
}
