package com.wolfott.mangement.line.requests;

import lombok.Data;

@Data
public class LineChartResponse {
    private String country;
    private Long count;
    private Double percentage;

    public LineChartResponse(String country, Long count, Double percentage) {
        this.country = country;
        this.count = count;
        this.percentage = percentage;
    }
}
