package com.wolfott.mangement.user.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "user_theme_options")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserThemeOptions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

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