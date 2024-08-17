package com.wolfott.mangement.server.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "mysql_syslog")
public class MysqlSyslog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "syslog_seq")
    @SequenceGenerator(name = "syslog_seq", sequenceName = "syslog_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Lob
    @Column(name = "error", columnDefinition = "LONGTEXT")
    private String error;

    @Column(name = "username")
    private String username;

    @Column(name = "ip")
    private String ip;

    @Column(name = "database")
    private String databaseName;

    @Column(name = "date")
    private Date date;

    @Column(name = "server_id")
    @ColumnDefault("1")
    private Integer serverId;

}
