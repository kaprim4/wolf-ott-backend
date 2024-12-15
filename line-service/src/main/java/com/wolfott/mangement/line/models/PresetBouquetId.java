package com.wolfott.mangement.line.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PresetBouquetId implements Serializable {

    @Column(name = "preset_id")
    private Long presetId;

    @Column(name = "bouquet_id")
    private Long bouquetId;

}
