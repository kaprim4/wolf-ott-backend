package com.wolfott.mangement.epg.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "epg")
public class Epg {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "epg_seq")
    @GenericGenerator(
            name = "epg_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "epg_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "20"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "epg_name")
    private String epgName;

    @Column(name = "epg_file")
    private String epgFile;

    @Column(name = "last_updated")
    private Integer lastUpdated;

    @Column(name = "days_keep")
    @ColumnDefault("7")
    private Integer daysKeep;

    @Lob
    @Column(name = "data", columnDefinition = "LONGTEXT")
    private String data;

    @Column(name = "offset")
    @ColumnDefault("0")
    private Integer offset;
}
