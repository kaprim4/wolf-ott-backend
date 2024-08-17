package com.wolfott.mangement.device.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
@NoArgsConstructor
@Table(name = "mag_logs")
public class MagLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mag_log_seq")
    @SequenceGenerator(name = "mag_log_seq", sequenceName = "mag_log_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "mag_id")
    private Long magId;

    @Column(name = "action")
    private String action;

    @Column(name = "is_active")
    @ColumnDefault("false")
    private Boolean isActive;

    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

}