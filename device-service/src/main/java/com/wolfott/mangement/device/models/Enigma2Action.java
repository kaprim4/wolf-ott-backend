package com.wolfott.mangement.device.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "enigma2_actions")
public class Enigma2Action implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;

    @Column(name = "device_id")
    private Integer deviceId;

    @Column(name = "type")
    @Lob
    private String type;

    @Column(name = "`key`")
    @Lob
    private String key;

    @Column(name = "command")
    @Lob
    private String command;

    @Column(name = "command2")
    @Lob
    private String command2;

}