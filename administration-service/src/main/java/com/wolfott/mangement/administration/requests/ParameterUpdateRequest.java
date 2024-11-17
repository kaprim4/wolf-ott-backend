package com.wolfott.mangement.administration.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
