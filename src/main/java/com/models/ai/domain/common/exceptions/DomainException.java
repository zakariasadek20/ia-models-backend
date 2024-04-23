package com.models.ai.domain.common.exceptions;

import java.util.Collections;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DomainException extends RuntimeException {

    protected final Map<String, String> data;
    protected final String code;
    protected final ErrorStatus errorStatus;
    protected final String defaultMessage;

    protected DomainException() {
        data = Collections.emptyMap();
        errorStatus = ErrorStatus.BAD_REQUEST;
        code = null;
        defaultMessage = null;
    }
}
