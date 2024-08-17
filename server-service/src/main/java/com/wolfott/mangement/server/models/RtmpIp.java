package com.wolfott.mangement.server.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "rtmp_ips")
public class RtmpIp implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "ip")
    private String ip;

    @Column(name = "password")
    private String password;

    @Lob
    @Column(name = "notes", columnDefinition = "MEDIUMTEXT")
    private String notes;

    @Column(name = "push")
    @ColumnDefault("false")
    private Boolean push;

    @Column(name = "pull")
    @ColumnDefault("false")
    private Boolean pull;

    @Column(name = "ip", unique = true)
    private String uniqueIp;

}