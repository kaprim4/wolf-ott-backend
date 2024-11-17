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

    @ColumnDefault("dark")
    @Column(name = "theme")
    private String theme;

    @ColumnDefault("blue_theme")
    @Column(name = "active_theme")
    private String activeTheme;

    @ColumnDefault("en-us")
    @Column(name = "language")
    private String language;

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