package com.wolfott.mangement.epg.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "epg_api")
public class EpgApi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "epg_api_seq")
    @SequenceGenerator(name = "epg_api_seq", sequenceName = "epg_api_seq", allocationSize = 1)
    @Column(name = "stationId")
    private Long stationId;

    @Column(name = "altId")
    private String altId;

    @Column(name = "callSign")
    private String callSign;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "bcastLangs", columnDefinition = "longtext")
    private String bcastLangs;

    @Column(name = "type")
    private String type;

    @Column(name = "signalType", columnDefinition = "varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin")
    private String signalType;

    @Column(name = "videoType")
    private String videoType;

    @Column(name = "affiliateId")
    private Integer affiliateId;

    @Column(name = "affiliateCallSign")
    private String affiliateCallSign;

    @Column(name = "picon")
    private String picon;

    @Column(name = "eng")
    @ColumnDefault("false")
    private Boolean eng;

}
