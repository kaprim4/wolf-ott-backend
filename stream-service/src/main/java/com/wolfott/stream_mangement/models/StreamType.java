package com.wolfott.stream_mangement.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "streams_types")
public class StreamType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "type_id")
    private String typeId;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "type_key")
    private String typeKey;

    @Column(name = "type_output")
    private String typeOutput;

    @Column(name = "live")
    @ColumnDefault("false")
    private Boolean live;

}
