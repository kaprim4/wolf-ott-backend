package com.wolfott.stream_mangement.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryUpdateResponse {
    private Long id;
    private String type;
    private String name;
    private Integer order;
    private Boolean isAdult;
    private Long parentId;
}
