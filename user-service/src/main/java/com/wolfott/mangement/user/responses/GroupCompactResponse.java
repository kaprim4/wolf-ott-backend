package com.wolfott.mangement.user.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupCompactResponse {
    private Long groupId;
    private String groupName;
    private Boolean isAdmin;
    private Boolean isReseller;
    private Integer totalAllowedGenTrials;
    private String totalAllowedGenIn;
    private Boolean canDelete;
    private Boolean canViewVod;
    private Boolean allowDownload;
    private Integer minimumUsernameLength;
    private Integer minimumPasswordLength;
    private String noticeHtml;
    private String subResellers;
}
