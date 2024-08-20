package com.wolfott.mangement.user.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCreateResponse {
    private Long profileId;
    private String profileName;
    private String profileOptions;
    private Boolean active;
}
