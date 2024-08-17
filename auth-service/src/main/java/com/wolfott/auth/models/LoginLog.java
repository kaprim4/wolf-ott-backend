package com.wolfott.auth.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "login_logs")
@Data
@NoArgsConstructor
public class LoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "access_code")
    private Integer accessCode;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "status")
    private String status;

    @Column(name = "login_ip")
    private String loginIp;

    @Column(name = "date", insertable = false, updatable = false) // Prevent automatic DDL changes
    private Long timestamp; // Internal representation in Unix timestamp

    @Transient // Mark as transient to prevent JPA from mapping this field
    private Date date; // Public getter/setter uses Date

    // Custom getter for Date
    public Date getDate() {
        return timestamp != null ? new Date(timestamp * 1000L) : null;
    }

    // Custom setter for Date
    public void setDate(Date date) {
        this.date = date;
        this.timestamp = date != null ? date.getTime() / 1000L : null;
    }
}
