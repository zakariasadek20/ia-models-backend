package com.models.ai.rest.config.exceptions;

import com.models.ai.rest.utils.ApiResponse;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionsHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception exception) {

        String exceptionID = UUID.randomUUID().toString();
        log.error("An Exception Occurred with ID <{}>", exceptionID, exception);

        String message = "Une exception technique est survenue. Contactez votre administrateur.\n"
                + "Le numero du Ticket est : "
                + exceptionID;

        message = Optional.ofNullable(exception.getMessage()).orElse(message);

        ApiResponse<Object> error = ApiResponse.error("ERROR_GLOBAL", message);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
