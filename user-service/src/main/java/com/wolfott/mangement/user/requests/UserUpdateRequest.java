package com.wolfott.mangement.user.requests;

import java.util.Date;
import lombok.Data;

@Data
public class UserUpdateRequest{
    private Long id;
    private String username;
    private String password;
    private String email;
    private String ip;
    //        Long memberGroupId,
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
    private Date dateRegistere;
}
