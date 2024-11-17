package com.wolfott.mangement.user.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserThemeOptionsRequest {
    private Long user_id;
    private String theme;
    private String activeTheme;
    private String language;
}
