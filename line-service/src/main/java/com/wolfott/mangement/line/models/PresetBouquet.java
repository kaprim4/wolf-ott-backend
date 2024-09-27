package com.wolfott.mangement.line.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "preset_bouquets")
@IdClass(PresetBouquetId.class) // Use a composite key
public class PresetBouquet implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preset_id", nullable = false)
    private Preset preset;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bouquet_id", nullable = false)
    private Bouquet bouquet;

    @Column(name = "position_order")
    private Integer positionOrder; // This field stores the order/index

    @Override
    public String toString() {
        return "PresetBouquet{" +
                "presetId=" + (preset != null ? preset.getId() : "null") +
                ", bouquetId=" + (bouquet != null ? bouquet.getId() : "null") +
                ", positionOrder=" + positionOrder +
                '}';
    }
}
