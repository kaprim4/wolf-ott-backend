package com.wolfott.mangement.epg.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageDetailResponse {
    private Long id;
    private String language;
    private String name;
    private Timestamp dateAdded;
    private Boolean active;
}
