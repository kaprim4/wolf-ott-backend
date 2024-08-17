package com.wolfott.mangement.user.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "users_credits_logs")
public class UserCreditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "target_id")
    private Long targetId;

    @Column(name = "admin_id")
    private Long adminId;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "date")
    private Date date;

    @Lob
    @Column(name = "reason", columnDefinition = "MEDIUMTEXT")
    private String reason;

}

