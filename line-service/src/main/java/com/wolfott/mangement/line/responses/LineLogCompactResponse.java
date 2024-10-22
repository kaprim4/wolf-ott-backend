package com.wolfott.mangement.line.responses;

import lombok.Data;

import java.util.Date;

@Data
public class LineLogCompactResponse {
    private Long id;
    private Long line_id;
    private String line_name;
    private Long stream_id;
    private String stream_name;
    private String player;
    private String isp;
    private String ip;
    private String country;
    private Date startAt;
    private Date endAt;
    private String output;
}
