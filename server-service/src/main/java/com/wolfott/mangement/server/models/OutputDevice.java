package com.wolfott.mangement.server.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ColumnTransformer;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "output_devices")
public class OutputDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_seq")
    @SequenceGenerator(name = "device_seq", sequenceName = "device_seq", allocationSize = 1)
    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "device_key")
    private String deviceKey;

    @Column(name = "device_filename")
    private String deviceFilename;

    @Lob
    @Column(name = "device_header", columnDefinition = "MEDIUMTEXT")
    private String deviceHeader;

    @Lob
    @Column(name = "device_conf", columnDefinition = "MEDIUMTEXT")
    private String deviceConf;

    @Lob
    @Column(name = "device_footer", columnDefinition = "MEDIUMTEXT")
    private String deviceFooter;

    @Column(name = "default_output")
    @ColumnDefault("false")
    @ColumnTransformer(read = "default_output = 0", write = "default_output = 0")
    private Boolean defaultOutput;

    @Lob
    @Column(name = "copy_text", columnDefinition = "MEDIUMTEXT")
    private String copyText;

}
