package com.wolfott.mangement.epg.requests;

public record ApiCreateRequest(    
        String altId,
        String callSign,
        String name,
        String bcastLangs,
        String type,
        String signalType,
        String videoType,
        Integer affiliateId,
        String affiliateCallSign,
        String picon,
        Boolean eng) {
}