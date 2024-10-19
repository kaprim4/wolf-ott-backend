package com.wolfott.stream_mangement.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
@Data
@NoArgsConstructor
//@Entity
@Table(name = "recordings")
public class Recording {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recording_seq")
    @SequenceGenerator(name = "recording_seq", sequenceName = "recording_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "stream_id")
    private Long streamId;

    @Column(name = "created_id")
    private Long createdId;

    @Lob
    @Column(name = "category_id")
    private String categoryId;

    @Lob
    @Column(name = "bouquets")
    private String bouquets;

    @Lob
    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "stream_icon")
    private String streamIcon;

    @Column(name = "start")
    private Long start;

    @Column(name = "end")
    private Long end;

    @Column(name = "source_id")
    private Long sourceId;

    @Column(name = "archive")
    private Long archive;

    @Column(name = "status")
    @ColumnDefault("false")
    private Boolean status;

}
