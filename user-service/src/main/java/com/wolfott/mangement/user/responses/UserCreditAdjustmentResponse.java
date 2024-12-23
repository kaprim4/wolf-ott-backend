package com.wolfott.mangement.user.responses;

import com.wolfott.mangement.user.models.UserCreditLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreditAdjustmentResponse {
    private Long userId;
    private Long credits;
    private UserCreditLog userCreditLog;
}
