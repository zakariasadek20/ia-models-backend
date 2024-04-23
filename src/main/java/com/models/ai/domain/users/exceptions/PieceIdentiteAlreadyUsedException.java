package com.models.ai.domain.users.exceptions;

import com.models.ai.domain.common.exceptions.DomainException;
import com.models.ai.domain.common.exceptions.ErrorStatus;

public class PieceIdentiteAlreadyUsedException extends DomainException {

    public PieceIdentiteAlreadyUsedException() {
        super(null, "CIN_ALREADY_USED", ErrorStatus.BAD_REQUEST, "la cin existe deja.");
    }
}
