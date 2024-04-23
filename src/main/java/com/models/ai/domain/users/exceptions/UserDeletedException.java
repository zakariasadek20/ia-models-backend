package com.models.ai.domain.users.exceptions;

import com.models.ai.domain.common.exceptions.DomainException;
import com.models.ai.domain.common.exceptions.ErrorStatus;

public class UserDeletedException extends DomainException {

    public UserDeletedException() {
        super(null, "USER_DELETED", ErrorStatus.BAD_REQUEST, "L'utilisateur est supprimé.");
    }
}
