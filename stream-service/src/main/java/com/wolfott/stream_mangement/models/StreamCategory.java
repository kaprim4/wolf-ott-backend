package com.wolfott.stream_mangement.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "streams_categories")
public class StreamCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "streams_category_seq")
    @SequenceGenerator(name = "streams_category_seq", sequenceName = "streams_category_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_type")
    private String categoryType;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "cat_order")
    private Integer catOrder;

    @Column(name = "is_adult")
    @ColumnDefault("false")
    private Boolean isAdult;

}