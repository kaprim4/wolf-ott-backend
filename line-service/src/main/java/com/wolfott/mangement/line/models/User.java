package com.wolfott.mangement.line.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.Subject;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
public class User implements UserDetails, Authentication {

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


    @OneToMany(mappedBy = "user")
    private List<Preset> presets = new ArrayList<>();


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



    // ---------------------------------
    // Implementation of UserDetails methods
    // ---------------------------------

    @Transient
    private boolean admin;
    @Transient
    private boolean expired;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Typically, you'd return a list of roles/permissions here
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Can implement logic to check if the account is expired
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Can implement logic to check if the account is locked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Can implement logic to check if credentials are expired
        return !expired;
    }

    @Override
    public boolean isEnabled() {
        // Can implement logic to check if the account is enabled
        return !expired;
    }

    @Override
    public String getName() {
        return this.username;
    }

    // ---------------------------------
    // Implementation of Authentication methods
    // ---------------------------------

    @Override
    public Object getCredentials() {
        return this.password;
    }

    @Override
    public User getDetails() {
        return this; // You could return other user-related details if needed
    }

    @Override
    public User getPrincipal() {
        return this; // You could return a user object or a principal here
    }

    @Override
    public boolean isAuthenticated() {
        // Should return if the user is authenticated, but this is generally handled by Spring Security
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        // If needed, you can handle logic for setting authenticated state,
        // but Spring Security handles most authentication logic
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot change the authenticated state");
        }
    }

    @Override
    public boolean implies(Subject subject) {
        return Authentication.super.implies(subject);
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "', email='" + email + "', status=" + status + "}";
    }
}
