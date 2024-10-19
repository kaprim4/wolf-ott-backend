package com.wolfott.stream_mangement.responses;

import lombok.Data;

import java.util.List;

@Data
public class StreamDetailResponse {
    private Long id;
    private String name;
    private String icon;
    private List<Integer> categories;
}
