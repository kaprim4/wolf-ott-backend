package com.wolfott.mangement.line.responses;

import com.wolfott.mangement.line.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PresetCompactResponse {
    private Long id;
    private UserDTO user;
    private String presetName;
    private String presetDescription;
    private List<Long> bouquets;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
