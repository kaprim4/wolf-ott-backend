package com.wolfott.mangement.line.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PresetCreateRequest {
    private String presetName;
    private String presetDescription;
    private Boolean status;
    private List<Long> bouquets = new ArrayList<>();
}
