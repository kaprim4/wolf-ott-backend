package com.wolfott.mangement.user.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.Subject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Table(name = "users")
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

    @ManyToOne(fetch = FetchType.LAZY)
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

    @Transient
    private User owner;

    @Column(name = "user_theme_options_id")
    private Long userThemeOptionsId;

    @Lob
    @Column(name = "thumbnail_image", columnDefinition = "LONGTEXT")
    private String thumbnail;


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

    public User(){}
    public User(Long id){
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
