package com.models.ai.domain.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus {
    INITIATED("INITATED"),
    VALID("VALID"),
    DELETED("DELETED"),
    DEACTIVATED("DEACTIVATED"),
    ;

    private final String code;
}
