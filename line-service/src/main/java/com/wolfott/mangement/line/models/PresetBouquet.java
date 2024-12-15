package com.wolfott.mangement.line.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "preset_bouquets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PresetBouquet {

    @EmbeddedId
    private PresetBouquetId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("presetId")
    @JoinColumn(name = "preset_id", nullable = false)
    private Preset preset;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bouquetId")
    @JoinColumn(name = "bouquet_id", nullable = false)
    private Bouquet bouquet;

    @Column(name = "position_order")
    private Integer positionOrder;
}
