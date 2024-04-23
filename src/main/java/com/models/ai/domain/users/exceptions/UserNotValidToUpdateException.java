package com.models.ai.domain.users.exceptions;

import com.models.ai.domain.common.exceptions.DomainException;
import com.models.ai.domain.common.exceptions.ErrorStatus;
import java.util.Map;

public class UserNotValidToUpdateException extends DomainException {

    public UserNotValidToUpdateException() {
        super(
                null,
                "USER_NOT_VALID_TO_UPDATE",
                ErrorStatus.BAD_REQUEST,
                "L'utilisateur n'est pas valide pour faire la modification.");
    }

    public UserNotValidToUpdateException(Map<String, String> data) {
        super(
                data,
                "USER_NOT_VALID_TO_UPDATE",
                ErrorStatus.BAD_REQUEST,
                "L'utilisateur n'est pas valide pour faire la modification.");
    }
}
