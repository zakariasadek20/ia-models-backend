package com.models.ai.domain.users.exceptions;

import com.models.ai.domain.common.exceptions.DomainException;
import com.models.ai.domain.common.exceptions.ErrorStatus;

public class InvalidProfileException extends DomainException {
    public InvalidProfileException() {
        super(null, "INVALID_PROFILE", ErrorStatus.BAD_REQUEST, "Profile Invalide");
    }

    public InvalidProfileException(String message) {
        super(null, "INVALID_PROFILE", ErrorStatus.BAD_REQUEST, message);
    }
}
