package com.wolfott.mangement.administration.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatResponce {
    private int openConnections;
    private int onlineUsers;
    private int activeAccounts;
    private int creditsAssigned;
}
