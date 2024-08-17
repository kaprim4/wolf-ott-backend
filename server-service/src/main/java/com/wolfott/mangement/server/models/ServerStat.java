package com.wolfott.mangement.server.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@NoArgsConstructor
@Table(name = "servers_stats")
public class ServerStat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "server_stats_seq")
    @GenericGenerator(
            name = "server_stats_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "server_stats_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "server_id")
    @ColumnDefault("0")
    private Integer serverId;

    @Column(name = "connections")
    @ColumnDefault("0")
    private Integer connections;

    @Column(name = "streams")
    @ColumnDefault("0")
    private Integer streams;

    @Column(name = "users")
    @ColumnDefault("0")
    private Integer users;

    @Column(name = "cpu")
    @ColumnDefault("0")
    private Float cpu;

    @Column(name = "cpu_cores")
    @ColumnDefault("0")
    private Integer cpuCores;

    @Column(name = "cpu_avg")
    @ColumnDefault("0")
    private Float cpuAvg;

    @Column(name = "total_mem")
    @ColumnDefault("0")
    private Integer totalMem;

    @Column(name = "total_mem_free")
    @ColumnDefault("0")
    private Integer totalMemFree;

    @Column(name = "total_mem_used")
    @ColumnDefault("0")
    private Integer totalMemUsed;

    @Column(name = "total_mem_used_percent")
    @ColumnDefault("0")
    private Float totalMemUsedPercent;

    @Column(name = "total_disk_space")
    @ColumnDefault("0")
    private Long totalDiskSpace;

    @Column(name = "uptime")
    private String uptime;

    @Column(name = "total_running_streams")
    @ColumnDefault("0")
    private Integer totalRunningStreams;

    @Column(name = "bytes_sent")
    @ColumnDefault("0")
    private Long bytesSent;

    @Column(name = "bytes_received")
    @ColumnDefault("0")
    private Long bytesReceived;

    @Column(name = "bytes_sent_total")
    @ColumnDefault("0")
    private Long bytesSentTotal;

    @Column(name = "bytes_received_total")
    @ColumnDefault("0")
    private Long bytesReceivedTotal;

    @Column(name = "cpu_load_average")
    @ColumnDefault("0")
    private Float cpuLoadAverage;

    @Lob
    @Column(name = "gpu_info")
    private String gpuInfo;

    @Lob
    @Column(name = "iostat_info")
    private String iostatInfo;

    @Column(name = "time")
    @ColumnDefault("0")
    private Integer time;

    @Column(name = "total_users")
    @ColumnDefault("0")
    private Integer totalUsers;
}
