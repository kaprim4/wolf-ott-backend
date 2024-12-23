package com.wolfott.mangement.user.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "users_credits_logs")
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private Long timestamp;

    @Lob
    @Column(name = "reason", columnDefinition = "MEDIUMTEXT")
    private String reason;


    // Custom getter for Date
    public Date getDate() {
        return timestamp != null ? new Date(timestamp * 1000L) : null;
    }

    // Custom setter for Date
    public void setDateRegistered(Date date) {
        this.timestamp = date != null ? date.getTime() / 1000L : null;
    }

}

