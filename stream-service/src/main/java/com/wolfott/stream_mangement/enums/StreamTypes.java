package com.wolfott.stream_mangement.enums;

public enum StreamTypes {
    LIVE("live"),
    MOVIE("movie"),
    CREATED_LIVE("created_live"),
    RADIO_STREAMS("radio_streams"),
    SERIES("series");

    private final String key;
    StreamTypes(String key){
        this.key = key;
    }

    public String getKey(){
        return this.key;
    }

    @Override
    public String toString() {
        return this.key;
    }
}
