package com.wolfott.mangement.server.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "syskill_log")
public class SyskillLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "syskill_log_seq")
    @GenericGenerator(
            name = "syskill_log_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "syskill_log_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "process")
    private String process;

    @Column(name = "pid")
    private Integer pid;

    @Column(name = "cpu")
    private Float cpu;

    @Column(name = "mem")
    private Integer mem;

    @Column(name = "reason")
    private String reason;

    @Lob
    @Column(name = "command", columnDefinition = "MEDIUMTEXT")
    private String command;

    @Column(name = "date")
    private Integer date;

}
