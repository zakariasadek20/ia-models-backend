package com.models.ai.domain.users.exceptions;

import com.models.ai.domain.common.exceptions.DomainException;
import com.models.ai.domain.common.exceptions.ErrorStatus;
import java.util.Map;

public class UserNotValidatedException extends DomainException {

    public UserNotValidatedException() {
        super(null, "USER_NOT_VALIDATED", ErrorStatus.BAD_REQUEST, "L'utilisateur n'est pas valide.");
    }

    public UserNotValidatedException(Map<String, String> data) {
        super(data, "USER_NOT_VALIDATED", ErrorStatus.BAD_REQUEST, "L'utilisateur n'est pas valide.");
    }
}
