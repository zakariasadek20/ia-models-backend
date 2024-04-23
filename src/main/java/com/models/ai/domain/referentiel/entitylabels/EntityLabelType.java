package com.models.ai.domain.referentiel.entitylabels;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EntityLabelType {
    USER_STATUS("USER_STATUS");

    private final String code;
}
