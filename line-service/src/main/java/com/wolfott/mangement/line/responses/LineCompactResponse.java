package com.wolfott.mangement.line.responses;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineCompactResponse {
    private Long id;
    private String username;
    private String password;
    private String owner;
    private Integer status;
    private boolean online;
    private boolean trial;
    private Integer active;
    private Integer connections;
    private Date expiration;
    private Date lastConnection;
}
