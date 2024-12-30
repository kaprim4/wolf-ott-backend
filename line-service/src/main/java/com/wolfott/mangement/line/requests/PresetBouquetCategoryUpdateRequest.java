package com.wolfott.mangement.line.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PresetBouquetCategoryUpdateRequest {
//    private Long id;
    private String presetName;
    private String presetDescription;
    private String categoryType;
    private Long bouquetId;
    private List<Integer> categories;
}
