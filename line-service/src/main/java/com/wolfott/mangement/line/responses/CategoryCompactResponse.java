package com.wolfott.mangement.line.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryCompactResponse {
    private Long id;
    private String type;
    private String name;
    private Integer order;
    private Boolean isAdult;
}
