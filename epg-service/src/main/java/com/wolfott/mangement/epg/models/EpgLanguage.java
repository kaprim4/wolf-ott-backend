package com.wolfott.mangement.epg.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "epg_languages")
public class EpgLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "epg_language_seq")
    @SequenceGenerator(name = "epg_language_seq", sequenceName = "epg_language_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "language")
    private String language;

    @Column(name = "name")
    private String name;

    @Column(name = "dateadded", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp dateAdded;

    @Column(name = "active")
    @ColumnDefault("false")
    private Boolean active;

}
