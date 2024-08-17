package com.wolfott.auth.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Table(name = "access_codes")
public class AccessCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "type")
    private Integer type;

    @Column(name = "enabled")
    @ColumnDefault("false")
    private Boolean enabled;

    @Lob
    @Column(name = "`groups`", columnDefinition = "mediumtext")
    private String groups;

    @Lob
    @Column(name = "whitelist", columnDefinition = "mediumtext")
    private String whitelist;

}
