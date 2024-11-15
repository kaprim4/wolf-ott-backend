package com.wolfott.mangement.user.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParameterUpdateResponse {
    private Long id;
    private String title;
    private String description;
    private String moduleName;
    private String key;
    private Object value;
    private String type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
