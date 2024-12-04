package com.wolfott.mangement.administration.responses;

import lombok.Data;

@Data
public class GlobalStateResponse {
    private Long onlineUsers;
    private Long onlineLines;
    private Long activeLines;
    private Long assignedCredits;
}
