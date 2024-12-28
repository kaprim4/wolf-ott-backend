package com.wolfott.mangement.line.responses;

import com.wolfott.mangement.line.dto.UserDTO;
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
public class PresetBouquetCategoryCreateResponse {
    private Long id;
    private String presetName;
    private String presetDescription;
    private Long ownerId;
    private Long bouquetId;
    private String categoryType;
    private List<Long> categories;
}