package com.wolfott.stream_mangement.responses;

import lombok.Data;

import java.util.List;

@Data
public class SerieCompactResponse {
    private Long id;
    private String title;
    private List<Integer> categories;
    private String cover;
    private String genre;
    private Integer year;
    private Integer rating;
}
