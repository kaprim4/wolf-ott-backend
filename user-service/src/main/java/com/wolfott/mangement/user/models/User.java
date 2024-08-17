package com.wolfott.mangement.user.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

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

    @Column(name = "date_registered")
    private Date dateRegistered;

    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "member_group_id")
    private Long memberGroupId;

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

}
