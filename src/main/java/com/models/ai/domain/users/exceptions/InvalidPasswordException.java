package com.models.ai.domain.users.exceptions;

import com.models.ai.domain.common.exceptions.DomainException;
import com.models.ai.domain.common.exceptions.ErrorStatus;

public class InvalidPasswordException extends DomainException {
    public InvalidPasswordException() {
        super(null, "INVALID_PASSWORD", ErrorStatus.BAD_REQUEST, "Mot de passe Invalid");
    }

    public InvalidPasswordException(String message) {
        super(null, "INVALID_PASSWORD", ErrorStatus.BAD_REQUEST, message);
    }
}
