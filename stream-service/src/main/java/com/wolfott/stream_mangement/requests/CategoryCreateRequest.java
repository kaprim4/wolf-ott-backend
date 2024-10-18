package com.wolfott.stream_mangement.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryCreateRequest {
    private String type;
    private String name;
    private Integer order;
    private Boolean isAdult;
    private Long parentId;
}
