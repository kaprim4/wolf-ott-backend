package com.wolfott.mangement.user.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "user_theme_options")
public class UserThemeOptions {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ColumnDefault("ltr")
    @Column(name = "dir")
    private String dir;

    @ColumnDefault("dark")
    @Column(name = "theme")
    private String theme;

    @ColumnDefault("false")
    @Column(name = "is_sidenav_opened")
    private Boolean sidenavOpened;

    @ColumnDefault("false")
    @Column(name = "is_sidenav_collapsed")
    private Boolean sidenavCollapsed;

    @ColumnDefault("false")
    @Column(name = "is_boxed")
    private Boolean boxed;

    @ColumnDefault("false")
    @Column(name = "is_horizontal")
    private Boolean horizontal;

    @ColumnDefault("false")
    @Column(name = "is_card_border")
    private Boolean cardBorder;

    @ColumnDefault("blue_theme")
    @Column(name = "active_theme")
    private String activeTheme;

    @ColumnDefault("en-us")
    @Column(name = "language")
    private String language;

    @ColumnDefault("side")
    @Column(name = "nav_pos")
    private String navPos;

}
/*
export const defaults: AppSettings = {
    dir: 'ltr',
    theme: 'dark',
    sidenavOpened: false,
    sidenavCollapsed: false,
    boxed: false,
    horizontal: false,
    cardBorder: false,
    activeTheme: 'blue_theme',
    language: 'en-us',
    navPos: 'side',
};
        */