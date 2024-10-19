package com.wolfott.stream_mangement.responses;

import lombok.Data;

@Data
public class SerieDetailResponse {
    private Long id;
    private String title;
    private String categoryId;
    private String cover;
    private String genre;
    private Integer year;
    private Integer rating;
    private String director;
    private String releaseDate;
    private Integer tmdbId;
    private String seasons;
    private String youtubeTrailer;
}
