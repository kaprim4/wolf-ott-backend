package com.wolfott.mangement.line.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PresetBouquetCategoryUpdateResponse {
    private Long id;
    private String presetName;
    private String presetDescription;
//    private Long ownerId;
//    private Long bouquetId;
//    private String categoryType;
    private List<Long> categories;
}