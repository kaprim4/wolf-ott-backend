package com.wolfott.mangement.user.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupCreateResponse {
    private Long groupId;
    private String groupName;
    private Boolean isAdmin;
    private Boolean isReseller;
    private Integer totalAllowedGenTrials;
    private String totalAllowedGenIn;
    private Boolean deleteUsers;
    private String allowedPages;
    private Boolean canDelete;
    private Boolean createSubResellers;
    private Float createSubResellersPrice;
    private Boolean resellerClientConnectionLogs;
    private Boolean canViewVod;
    private Boolean allowDownload;
    private Integer minimumTrialCredits;
    private Boolean allowRestrictions;
    private Boolean allowChangeUsername;
    private Boolean allowChangePassword;
    private Integer minimumUsernameLength;
    private Integer minimumPasswordLength;
    private Boolean allowChangeBouquets;
    private String noticeHtml;
    private String subResellers;
}
