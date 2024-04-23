package com.models.ai.domain.users.exceptions;

import com.models.ai.domain.common.exceptions.DomainException;
import com.models.ai.domain.common.exceptions.ErrorStatus;

public class UserAuthenticatedException extends DomainException {

    public UserAuthenticatedException() {
        super(null, "USER_NOT_AUTHENTICATED", ErrorStatus.BAD_REQUEST, "L'utilisateur n'est pas connecte.");
    }
}
