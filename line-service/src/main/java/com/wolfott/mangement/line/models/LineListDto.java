package com.wolfott.mangement.line.models;

import lombok.Builder;
import java.sql.Timestamp;

@Builder
public class LineListDto {
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
