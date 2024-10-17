package com.wolfott.stream_mangement.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StreamCompactResponse {
    private Long id;
    private String bouquetName;
    private Integer bouquetOrder;
}
