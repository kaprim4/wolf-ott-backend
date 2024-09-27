package com.wolfott.mangement.line.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "presets")
@NoArgsConstructor
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

    @OneToMany(mappedBy = "preset", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<PresetBouquet> presetBouquets = new HashSet<>();

    public Preset(Long id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Preset{" +
                "id=" + id +
                ", presetName='" + presetName + '\'' +
                ", presetDescription='" + presetDescription + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
