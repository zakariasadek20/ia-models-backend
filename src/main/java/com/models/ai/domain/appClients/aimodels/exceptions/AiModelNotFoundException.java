package com.models.ai.domain.appClients.aimodels.exceptions;

import com.models.ai.domain.common.exceptions.DomainException;
import com.models.ai.domain.common.exceptions.ErrorStatus;

public class AiModelNotFoundException extends DomainException {

    public AiModelNotFoundException() {
        super(null, "AI_MODEL_NOT_FOUND", ErrorStatus.BAD_REQUEST, "AI model existe deja.");
    }
}
