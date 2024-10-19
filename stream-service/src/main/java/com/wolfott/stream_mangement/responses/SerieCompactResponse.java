package com.wolfott.stream_mangement.responses;

import lombok.Data;

@Data
public class SerieCompactResponse {
    private Long id;
    private String title;
    private String categoryId;
    private String cover;
    private String genre;
    private Integer year;
    private Integer rating;
}
