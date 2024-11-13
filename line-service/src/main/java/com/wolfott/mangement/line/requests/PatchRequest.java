package com.wolfott.mangement.line.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchRequest {
    private String op;
    private String path;
    private Object value;
}
