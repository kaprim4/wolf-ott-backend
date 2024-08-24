package com.wolfott.mangement.epg.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelDetailResponse {
    private Long id;
    private String channelId;
    private String name;
    private String langs;
    private Boolean active;
}
