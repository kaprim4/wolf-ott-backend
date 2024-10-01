package com.wolfott.mangement.user.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    private String username;
    private String password;
    private String email;
    private String ip;
    private Float credits;
    private String notes;
    private Boolean status;
    private String resellerDns;
    private Long ownerId;
    private String overridePackages;
    private String hue;
    private Integer theme;
    private String timezone;
    private String apiKey;
    private Date lastLogin;
    private Date dateRegistered;
}
