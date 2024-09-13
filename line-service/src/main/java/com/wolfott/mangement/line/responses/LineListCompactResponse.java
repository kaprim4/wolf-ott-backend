package com.wolfott.mangement.line.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineListCompactResponse {

    private Long id;
    private String username;
    private String password;
    private String owner;
    private Integer status;
    private boolean online;
    private boolean trial;
    private Integer active;
    private Integer connections;
    private Long expiration;
    private Timestamp lastConnection;
}
