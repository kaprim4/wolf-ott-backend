package com.wolfott.mangement.user.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "profiles")
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "profile_name")
    private String profileName;

    @Lob
    @Column(name = "profile_options", columnDefinition = "MEDIUMTEXT")
    private String profileOptions;

    @Column(name = "active")
    @ColumnDefault("false")
    private Boolean active;
}