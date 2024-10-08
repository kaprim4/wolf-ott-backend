package com.wolfott.mangement.user.requests;

public record GroupUpdateRequest(Long groupId,
                                 String groupName,
                                 Boolean isAdmin,
                                 Boolean isReseller,
                                 Integer totalAllowedGenTrials,
                                 String totalAllowedGenIn,
                                 Boolean deleteUsers,
                                 String allowedPages,
                                 Boolean canDelete,
                                 Boolean createSubResellers,
                                 Float createSubResellersPrice,
                                 Boolean resellerClientConnectionLogs,
                                 Boolean canViewVod,
                                 Boolean allowDownload,
                                 Integer minimumTrialCredits,
                                 Boolean allowRestrictions,
                                 Boolean allowChangeUsername,
                                 Boolean allowChangePassword,
                                 Integer minimumUsernameLength,
                                 Integer minimumPasswordLength,
                                 Boolean allowChangeBouquets,
                                 String noticeHtml,
                                 String subResellers) {
}
