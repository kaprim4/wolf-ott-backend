package com.wolfott.mangement.epg.requests;

public record EpgCreateRequest(
        String epgName,
        String epgFile,
        Integer lastUpdated,
        Integer daysKeep,
        String data,
        Integer offset
) {
}
