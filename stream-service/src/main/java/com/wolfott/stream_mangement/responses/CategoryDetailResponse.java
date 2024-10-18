package com.wolfott.stream_mangement.responses;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryDetailResponse {
    private Long id;
    private String type;
    private String name;
    private Integer order;
    private Boolean isAdult;
    private Long parentId;
}
