package com.wolfott.mangement.device.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
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

//    @Column(name = "date_registered")
//    private Date dateRegistered;

//    @Column(name = "last_login", columnDefinition = )
//    private Date lastLogin;

//    @Column(name = "member_group_id")
//    private Long memberGroupId;

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


    @Column(name = "last_login") // Prevent automatic DDL changes
    private Long timestampLastLogin; // Internal representation in Unix timestamp

    @Transient // Mark as transient to prevent JPA from mapping this field
    private Date lastLogin; // Public getter/setter uses Date

    // Custom getter for LastLogin
    public Date getLastLogin() {
        return timestampLastLogin != null ? new Date(timestampLastLogin * 1000L) : null;
    }

    // Custom setter for Date
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
        this.timestampLastLogin = lastLogin != null ? lastLogin.getTime() / 1000L : null;
    }

    @Column(name = "date_registered") // Prevent automatic DDL changes
    private Long timestampDateRegistered; // Internal representation in Unix timestamp

    @Transient // Mark as transient to prevent JPA from mapping this field
    private Date dateRegistered; // Public getter/setter uses Date

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<MagDevice> magDevices;
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Enigma2Device> enigma2Devices;
    // Custom getter for DateRegistered
    public Date getDateRegistered() {
        return timestampDateRegistered != null ? new Date(timestampDateRegistered * 1000L) : null;
    }

    // Custom setter for DateRegistered
    public void setDateRegistered(Date date) {
        this.dateRegistered = date;
        this.timestampDateRegistered = date != null ? date.getTime() / 1000L : null;
    }

    public User(Long id) {
        this.id = id;
    }
}
