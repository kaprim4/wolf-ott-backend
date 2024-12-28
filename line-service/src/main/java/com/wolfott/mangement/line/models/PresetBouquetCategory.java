package com.wolfott.mangement.line.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "preset_bouquet_categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PresetBouquetCategory {
    @Id
    private Long id;

    @Column(name = "preset_name")
    private String presetName;
    @Column(name = "preset_description")
    private String presetDescription;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "category_type")
    private String categoryType;

    @Lob
    @Column(name = "categories_array")
    private String categories;

    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("bouquetId")
    @JoinColumn(name = "bouquet_id", nullable = false)
    private Bouquet bouquet;
}
