package com.wolfott.mangement.line.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "presets")
@AllArgsConstructor
@NoArgsConstructor
public class Preset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "preset_name", length = 255)
    private String presetName;

    @Column(name = "preset_description", columnDefinition = "text")
    private String presetDescription;

    @Column(name = "preset_status", columnDefinition = "TINYINT(1)")
    private Boolean status;

    @Column(name = "preset_created_at")
    private LocalDateTime createdAt;

    @Column(name = "preset_updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "preset", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PresetBouquet> presetBouquets = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.setCreatedAt(LocalDateTime.now());
    }

    @PreUpdate
    protected void onUpdate() {
        this.setUpdatedAt(LocalDateTime.now());
    }

    public void addBouquet(Bouquet bouquet, Integer positionOrder) {
        PresetBouquet pb = new PresetBouquet();
        PresetBouquetId id = new PresetBouquetId();
        id.setPresetId(this.id);
        id.setBouquetId(bouquet.getId());
        pb.setId(id);
        pb.setPreset(this);
        pb.setBouquet(bouquet);
        pb.setPositionOrder(positionOrder);
        this.presetBouquets.add(pb);
    }
}