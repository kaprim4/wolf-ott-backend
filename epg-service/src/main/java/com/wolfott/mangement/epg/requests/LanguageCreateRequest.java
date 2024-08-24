package com.wolfott.mangement.epg.requests;

import java.sql.Timestamp;

public record LanguageCreateRequest(
        String language,
        String name,
        Timestamp dateAdded,
        Boolean active
) {
}
