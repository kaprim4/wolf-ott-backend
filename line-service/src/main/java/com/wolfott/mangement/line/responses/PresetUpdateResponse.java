package com.wolfott.mangement.line.responses;

import com.wolfott.mangement.line.dto.UserDTO;
import com.wolfott.mangement.line.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PresetUpdateResponse {
    private Long id;
    private UserDTO user;
    private String presetName;
    private String presetDescription;
    private Boolean status;
    private List<Long> bouquets = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
