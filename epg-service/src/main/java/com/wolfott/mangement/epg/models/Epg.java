package com.wolfott.mangement.epg.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "epg")
public class Epg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "epg")
    private List<EpgData> datas;

    @OneToMany(mappedBy = "epg")
    private List<EpgChannel> channels;
}
