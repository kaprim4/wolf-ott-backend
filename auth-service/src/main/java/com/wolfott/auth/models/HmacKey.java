package com.wolfott.auth.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

//@Entity
@Data
@Table(name = "hmac_keys")
public class HmacKey {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hmac_key_seq")
    @SequenceGenerator(name = "hmac_key_seq", sequenceName = "hmac_key_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
 
    @Column(name = "key")
    private String key;

    @Column(name = "notes")
    private String notes;

    @Column(name = "enabled")
    @ColumnDefault("false")
    private Boolean enabled;
}

