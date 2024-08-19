package com.wolfott.mangement.line.requests;

import java.util.Map;

public record LineFilterRequest(
        Map<String, FilterCriteria> criteria
) {
    public record FilterCriteria(
            Object value,
            FilterOperation operation
    ) {
        public enum FilterOperation {
            EQUAL, NOT_EQUAL, GREATER_THAN, LESS_THAN, LIKE, CONTAINS
        }
    }
}
