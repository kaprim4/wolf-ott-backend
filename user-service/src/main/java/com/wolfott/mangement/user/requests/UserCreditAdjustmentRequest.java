package com.wolfott.mangement.user.requests;

public record UserCreditAdjustmentRequest(
        Long userId,
        Long total_credits,
        Long cost_credits,
        Long remaining_credits,
        String reason
) {
}
