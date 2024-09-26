package com.wolfott.mangement.line.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "presets")
public class Preset implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "preset_name")
    private String presetName;

    @Column(name = "preset_description")
    private String presetDescription;

    @Column(name = "preset_status")
    private Boolean status;
    @Column(name = "preset_created_at")
    private LocalDateTime createdAt;
    @Column(name = "preset_updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "preset", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PresetBouquet> presetBouquets = new HashSet<>();
}
