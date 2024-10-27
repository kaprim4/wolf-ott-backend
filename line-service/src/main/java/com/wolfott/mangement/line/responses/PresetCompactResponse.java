package com.wolfott.mangement.line.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PresetCompactResponse {
    private Long id;
    private String presetName;
    private String presetDescription;
    private String bouquets;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
