package com.wolfott.mangement.user.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Data
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "user_theme_options")
@NoArgsConstructor
@AllArgsConstructor
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