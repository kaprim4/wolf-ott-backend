package com.wolfott.stream_mangement.responses;

import lombok.Data;

@Data
public class EpisodeCompactResponse {
    private Long id;
    private String title;
    private Integer season;
    private Integer number;
    private Boolean active;
    private String serie;
}
