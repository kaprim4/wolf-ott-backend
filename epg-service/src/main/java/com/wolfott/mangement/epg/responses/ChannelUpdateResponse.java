package com.wolfott.mangement.epg.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelUpdateResponse {
    private Long id;
    private String channelId;
    private String name;
    private String langs;
    private Boolean active;
}
