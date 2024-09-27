package com.wolfott.mangement.line.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class PresetBouquetId implements Serializable {
    private Long preset;
    private Long bouquet;
}
