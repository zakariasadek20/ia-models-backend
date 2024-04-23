package com.models.ai.domain.users.exceptions;

import com.models.ai.domain.common.exceptions.DomainException;
import com.models.ai.domain.common.exceptions.ErrorStatus;

public class UserNotFoundException extends DomainException {

    public UserNotFoundException() {
        super(null, "USER_NOT_FOUND", ErrorStatus.BAD_REQUEST, "L'utilisateur n'existe pas.");
    }
}
