package com.wolfott.mangement.epg.requests;

public record EpgUpdateRequest(
        Long id,
        String epgName,
        String epgFile,
        Integer lastUpdated,
        Integer daysKeep,
        String data,
        Integer offset
) {
}
