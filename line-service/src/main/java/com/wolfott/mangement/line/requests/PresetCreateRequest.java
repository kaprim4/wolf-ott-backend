package com.wolfott.mangement.line.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PresetCreateRequest {
    private String presetName;
    private String presetDescription;
    private Boolean status;
}
