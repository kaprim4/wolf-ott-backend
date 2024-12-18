package com.wolfott.mangement.user.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCompactResponse {
    private Long id;
    private String username;
    private String owner;
    //    private String password;
    private String email;
    private String ip;
    //    private Date dateRegistered;
//    private Date lastLogin;
    private Long memberGroupId;
    private Float credits;
    private String notes;
    private Boolean status;
    //    private String apiKey;
    private Date lastLogin;
    private Date dateRegistered;
}
