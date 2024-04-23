package com.models.ai.domain.appClients.exceptions;

import com.models.ai.domain.common.exceptions.DomainException;
import com.models.ai.domain.common.exceptions.ErrorStatus;

public class AppClientsNotFoundException extends DomainException {

    public AppClientsNotFoundException() {
        super(null, "APP_CLIENTS_NOT_FOUND", ErrorStatus.BAD_REQUEST, "Application existe deja.");
    }
}
