package com.wolfott.auth.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

//@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "blocked_uas")
public class BlockedUa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blocked_ua_seq")
    @SequenceGenerator(name = "blocked_ua_seq", sequenceName = "blocked_ua_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "exact_match")
    @ColumnDefault("false")
    private Boolean exactMatch;

    @Column(name = "attempts_blocked")
    @ColumnDefault("0")
    private Integer attemptsBlocked;

}