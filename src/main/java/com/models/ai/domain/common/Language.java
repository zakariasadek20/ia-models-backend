package com.models.ai.domain.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Language {
    AR("AR"),
    FR("FR"),
    EN("EN");

    private final String code;

    public static Language from(String lanauage) {
        return Language.valueOf(lanauage.toUpperCase());
    }
}
