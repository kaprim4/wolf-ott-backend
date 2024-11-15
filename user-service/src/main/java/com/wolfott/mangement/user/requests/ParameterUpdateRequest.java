package com.wolfott.mangement.user.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParameterUpdateRequest {
    private Long id;
    private String title;
    private String description;
    private String moduleName;
    private String key;
    private Object value;
    private String type;
}
