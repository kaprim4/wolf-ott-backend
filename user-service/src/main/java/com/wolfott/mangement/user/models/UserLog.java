package com.wolfott.mangement.user.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "users_logs")
public class UserLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_log_seq")
//    @SequenceGenerator(name = "user_log_seq", sequenceName = "user_log_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "owner")
    private Long owner;

    @Column(name = "type")
    private String type;

    @Column(name = "action")
    private String action;

    @Column(name = "log_id")
    private Long logId;

    @Column(name = "package_id")
    private Long packageId;

    @Column(name = "cost")
    private Long cost;

    @Column(name = "credits_after")
    private Long creditsAfter;

    @Column(name = "date")
    private Long date;

    @Lob
    @Column(name = "deleted_info", columnDefinition = "LONGTEXT")
    private String deletedInfo;

}