package com.wolfott.mangement.user.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "ip")
    private String ip;

    @Column(name = "credits")
    private Float credits;

    @Lob
    @Column(name = "notes")
    private String notes;

    @Column(name = "status")
    @ColumnDefault("1")
    private Boolean status;

    @Lob
    @Column(name = "reseller_dns")
    private String resellerDns;

    @Column(name = "owner_id")
    @ColumnDefault("0")
    private Long ownerId;

    @Lob
    @Column(name = "override_packages")
    private String overridePackages;

    @Column(name = "hue")
    private String hue;

    @Column(name = "theme")
    @ColumnDefault("0")
    private Integer theme;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "api_key")
    private String apiKey;

    @ManyToOne
    @JoinColumn(name = "member_group_id")
    private UserGroup group;

    @Column(name = "last_login")
    private Long timestampLastLogin;

    @Column(name = "date_registered")
    private Long timestampDateRegistered;

    @Transient
    private String lastLogin;

    @Transient
    private String dateRegistered;


    public String getLastLogin() {
        if (timestampLastLogin != null) {
            Date date = new Date(timestampLastLogin * 1000L);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(date);
        }
        return null;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lastLogin) : null;
        this.timestampLastLogin = lastLogin != null ? lastLogin.getTime() / 1000L : null;
    }

    public String getDateRegistered() {
        if (timestampDateRegistered != null) {
            Date date = new Date(timestampDateRegistered * 1000L);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(date);
        }
        return null;
    }

    public void setDateRegistered(Date date) {
        this.dateRegistered = date != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date) : null;
        this.timestampDateRegistered = date != null ? date.getTime() / 1000L : null;
    }
}
