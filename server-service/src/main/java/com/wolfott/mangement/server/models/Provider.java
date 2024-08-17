package com.wolfott.mangement.server.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "providers")
public class Provider {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "ip")
    private String ip;

    @Column(name = "port")
    @ColumnDefault("80")
    private int port;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Lob
    @Column(name = "data", columnDefinition = "MEDIUMTEXT")
    private String data;

    @Column(name = "last_changed")
    private Date lastChanged;

    @Column(name = "legacy")
    @ColumnDefault("0")
    private Boolean legacy = false;

    @Column(name = "enabled")
    @ColumnDefault("1")
    private Boolean enabled = true;

    @Column(name = "status")
    @ColumnDefault("0")
    private Boolean status = false;

    @Column(name = "ssl")
    @ColumnDefault("0")
    private Boolean ssl = false;

    @Column(name = "hls")
    @ColumnDefault("0")
    private Boolean hls = false;
}
