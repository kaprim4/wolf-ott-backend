package com.wolfott.mangement.line.models;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class LineList {
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
    private String lastConnection;
}
