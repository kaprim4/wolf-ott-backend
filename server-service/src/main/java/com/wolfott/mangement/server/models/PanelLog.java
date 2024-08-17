package com.wolfott.mangement.server.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Data
@Table(name = "panel_logs")
public class PanelLog {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "type", nullable = false, length = 50)
    @ColumnDefault("'pdo'")
    private String type;

    @Lob
    @Column(name = "log_message")
    private String logMessage;

    @Lob
    @Column(name = "log_extra")
    private String logExtra;

    @Column(name = "line")
    private Integer line;

    @Column(name = "date")
    private Date date;

    @Column(name = "server_id")
    private Integer serverId;

    @Column(name = "unique")
    private String uniqueId;

}
