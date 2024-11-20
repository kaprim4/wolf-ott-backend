package com.wolfott.mangement.user.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserThemeOptionsRequest {
    private Long userId;
    private String theme;
    private String activeTheme;
    private String language;
}
