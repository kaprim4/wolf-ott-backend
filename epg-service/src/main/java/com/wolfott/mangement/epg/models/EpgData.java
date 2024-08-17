package com.wolfott.mangement.epg.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "epg_data")
public class EpgData {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "epg_id")
    private Integer epgId;

    @Column(name = "title")
    private String title;

    @Column(name = "lang")
    private String lang;

    @Column(name = "start")
    private Integer start;

    @Column(name = "end")
    private Integer end;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "channel_id")
    private String channelId;

}
