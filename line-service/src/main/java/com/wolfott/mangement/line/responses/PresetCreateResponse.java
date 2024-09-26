package com.wolfott.mangement.line.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PresetCreateResponse {
    private Long id;
    private String presetName;
    private String presetDescription;
    private Boolean status;
    private List<Long> bouquets = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
