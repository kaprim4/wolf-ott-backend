package com.wolfott.mangement.epg.requests;

import java.sql.Timestamp;

public record LanguageUpdateRequest(
        Long id,
        String language,
        String name,
        Timestamp dateAdded,
        Boolean active
) {
}
