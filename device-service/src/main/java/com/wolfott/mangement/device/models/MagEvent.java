package com.wolfott.mangement.device.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode
@ToString
@Table(name = "mag_events")
public class MagEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mag_event_seq")
    @SequenceGenerator(name = "mag_event_seq", sequenceName = "mag_event_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private Integer status = 0;

    @Column(name = "mag_device_id")
    private Long magDeviceId;

    @Column(name = "event")
    private String event;

    @Column(name = "need_confirm")
    private Integer needConfirm = 0;

    @Lob
    @Column(name = "msg")
    private String msg;

    @Column(name = "reboot_after_ok")
    private Integer rebootAfterOk = 0;

    @Column(name = "auto_hide_timeout")
    private Integer autoHideTimeout = 0;

    @Column(name = "send_time")
    private Long sendTime;

    @Column(name = "additional_services_on")
    private Integer additionalServicesOn = 1;

    @Column(name = "anec")
    private Integer anec = 0;

    @Column(name = "vclub")
    private Integer vclub = 0;

}
