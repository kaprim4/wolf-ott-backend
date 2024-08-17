package com.wolfott.mangement.epg.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "epg_channels")
public class EpgChannel {

    @Id
    @GeneratedValue(generator = "custom-sequence")
    @GenericGenerator(name = "custom-sequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "custom_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "2820"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            })
    @Column(name = "id")
    private Long id;

    @Column(name = "epg_id")
    private Long epgId;

    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "langs", columnDefinition = "MEDIUMTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin")
    private String langs;

    @Column(name = "active")
    @ColumnDefault("false")
    private Boolean active;

}
