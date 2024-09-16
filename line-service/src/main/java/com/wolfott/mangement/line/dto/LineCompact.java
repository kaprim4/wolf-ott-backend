package com.wolfott.mangement.line.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineCompact {
    private Long id;
    private String username;
    private String password;
    private String owner;
    private String status;
    private String online;
    private String trial;
    private String active;
    private String connections;
    private String expiration;
    private String lastConnection;
}
