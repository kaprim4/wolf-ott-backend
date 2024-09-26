package com.wolfott.mangement.line.requests;

import com.wolfott.mangement.line.models.PresetBouquet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PresetUpdateRequest {
    private Long id;
    private String presetName;
    private String presetDescription;
    private Boolean status;
    private List<Long> bouquets = new ArrayList<>();
}
