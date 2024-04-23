package com.models.ai.domain.users.exceptions;

import com.models.ai.domain.common.exceptions.DomainException;
import com.models.ai.domain.common.exceptions.ErrorStatus;

public class LoginAlreadyUsedException extends DomainException {

    public LoginAlreadyUsedException() {
        super(null, "USER_ALREADY_EXISTS", ErrorStatus.BAD_REQUEST, "Le login existe deja.");
    }
}
