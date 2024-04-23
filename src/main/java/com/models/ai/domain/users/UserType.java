package com.models.ai.domain.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserType {
    AGENT("AGENT"),
    ADMIN("ADMIN"),
    ;

    private final String code;
}
