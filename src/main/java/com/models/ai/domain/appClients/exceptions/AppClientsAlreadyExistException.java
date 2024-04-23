package com.models.ai.domain.appClients.exceptions;

import com.models.ai.domain.common.exceptions.DomainException;
import com.models.ai.domain.common.exceptions.ErrorStatus;

public class AppClientsAlreadyExistException extends DomainException {

    public AppClientsAlreadyExistException() {
        super(null, "APP_CLIENTS_ALREAD_EXISTS", ErrorStatus.BAD_REQUEST, "Application n'existe pas.");
    }
}
