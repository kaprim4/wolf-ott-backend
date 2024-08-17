package com.wolfott.stream_mangement.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "streams_arguments")
public class StreamArgument implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "argument_cat")
    private String argumentCat;

    @Column(name = "argument_name")
    private String argumentName;

    @Lob
    @Column(name = "argument_description", columnDefinition = "MEDIUMTEXT")
    private String argumentDescription;

    @Column(name = "argument_wprotocol")
    private String argumentWprotocol;

    @Column(name = "argument_key")
    private String argumentKey;

    @Column(name = "argument_cmd")
    private String argumentCmd;

    @Column(name = "argument_type")
    private String argumentType;

    @Column(name = "argument_default_value")
    private String argumentDefaultValue;

}
