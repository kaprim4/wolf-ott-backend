package com.wolfott.stream_mangement.responses;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SerieDetailResponse {
    private Long id;
    private String title;
    private List<Integer> categories;
    private String cover;
    private String genre;
    private Integer year;
    private Integer rating;
    private String director;
    private String releaseDate;
    private Integer tmdbId;
    private List<Object> seasons;
    private String youtubeTrailer;
}
