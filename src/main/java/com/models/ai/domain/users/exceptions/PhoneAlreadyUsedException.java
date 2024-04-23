package com.models.ai.domain.users.exceptions;

import com.models.ai.domain.common.exceptions.DomainException;
import com.models.ai.domain.common.exceptions.ErrorStatus;

public class PhoneAlreadyUsedException extends DomainException {

    public PhoneAlreadyUsedException() {
        super(null, "PHONE_ALREADY_USED", ErrorStatus.BAD_REQUEST, "Le telephone existe deja.");
    }
}
