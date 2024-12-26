package com.wolfott.mangement.user.responses;

import lombok.Data;

import java.util.Date;

@Data
public class UserLogCompactResponse {
    private Long id;
    private Long owner_id;
    private String owner_username;
    private String type;
    private Long line_id;
    private String line_username;
    private Long user_id;
    private String user_username;
    private Long package_id;
    private String package_name;
    private String action;
    private Long cost;
    private Long credit;
    private Long date;
}
