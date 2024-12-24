package com.wolfott.mangement.user.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCompactResponse {
    private Long id;
    private String username;
    private String owner;
    private String ownerName;
    // private String password;
    private String email;
    private String ip;
    private Long memberGroupId;
    private Float credits;
    private String notes;
    private Boolean status;
    private String apiKey;
    private Date lastLogin;
    private Date dateRegistered;
    private Long lineCount;
    private String thumbnail;
}
