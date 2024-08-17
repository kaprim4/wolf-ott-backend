package com.wolfott.auth.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode
//@Entity
@Table(name = "blocked_asns")
public class BlockedAsn {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blocked_asn_seq")
    @GenericGenerator(
            name = "blocked_asn_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "blocked_asn_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "asn")
    @ColumnDefault("0")
    private Integer asn;

    @Column(name = "isp")
    private String isp;

    @Column(name = "domain")
    private String domain;

    @Column(name = "country")
    private String country;

    @Column(name = "num_ips")
    @ColumnDefault("0")
    private Integer numIps;

    @Column(name = "type")
    private String type;

    @Column(name = "allocated")
    @ColumnDefault("0000-00-00 00:00:00")
    private Timestamp allocated;

    @Column(name = "blocked")
    @ColumnDefault("0")
    private Boolean blocked = false;

}
