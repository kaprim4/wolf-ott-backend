package com.wolfott.mangement.line.requests;

public record BouquetCreateRequest(
        String bouquetName,
        String bouquetChannels,
        String bouquetMovies,
        String bouquetRadios,
        String bouquetSeries,
        Integer bouquetOrder) {
}
