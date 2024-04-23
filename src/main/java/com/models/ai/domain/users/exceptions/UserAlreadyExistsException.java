package com.models.ai.domain.users.exceptions;

import com.models.ai.domain.common.exceptions.DomainException;
import com.models.ai.domain.common.exceptions.ErrorStatus;

public class UserAlreadyExistsException extends DomainException {

    public UserAlreadyExistsException() {
        super(null, "USER_ALREAD_EXISTS", ErrorStatus.BAD_REQUEST, "L'utilisateur existe deja.");
    }
}
