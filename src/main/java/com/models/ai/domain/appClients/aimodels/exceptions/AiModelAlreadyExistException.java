package com.models.ai.domain.appClients.aimodels.exceptions;

import com.models.ai.domain.common.exceptions.DomainException;
import com.models.ai.domain.common.exceptions.ErrorStatus;

public class AiModelAlreadyExistException extends DomainException {

    public AiModelAlreadyExistException() {
        super(null, "AI_MODEL_ALREAD_EXISTS", ErrorStatus.BAD_REQUEST, "AI model n'existe pas.");
    }
}
