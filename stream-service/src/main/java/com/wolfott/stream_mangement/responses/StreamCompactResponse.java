package com.wolfott.stream_mangement.responses;

import lombok.Data;

@Data
public class StreamCompactResponse {
    private Long id;
    private String name;
    private String icon;
    private String categoryId;
}
