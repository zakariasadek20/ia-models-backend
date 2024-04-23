package com.models.ai.domain.users.usersprofiles.exceptions;

import com.models.ai.domain.common.exceptions.DomainException;
import com.models.ai.domain.common.exceptions.ErrorStatus;

public class ProfileNotFoundException extends DomainException {
    public ProfileNotFoundException() {
        super(null, "PROFILE_NOT_FOUND", ErrorStatus.BAD_REQUEST, "Le profile n'existe pas.");
    }
}
