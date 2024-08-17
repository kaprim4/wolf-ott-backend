package com.wolfott.mangement.device.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "mag_claims")
public class MagClaim {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "mag_id")
    private Integer magId;

    @Column(name = "stream_id")
    private Integer streamId;

    @Column(name = "real_type")
    private String realType;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

}