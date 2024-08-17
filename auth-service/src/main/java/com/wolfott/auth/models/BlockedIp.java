package com.wolfott.auth.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

//@Entity
@Data
@Table(name = "blocked_ips")
public class BlockedIp {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private String id;

    @Column(name = "ip", unique = true)
    private String ip;

    @Lob
    @Column(name = "notes")
    private String notes;

    @Column(name = "date")
    private Date date;

}