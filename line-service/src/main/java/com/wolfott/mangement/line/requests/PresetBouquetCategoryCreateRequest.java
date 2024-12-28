package com.wolfott.mangement.line.requests;

import com.wolfott.mangement.line.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PresetBouquetCategoryCreateRequest {
    private Long id;
    private String presetName;
    private String presetDescription;
    private String categoryType;
    private Long bouquetId;
    private List<Long> categories;
}
