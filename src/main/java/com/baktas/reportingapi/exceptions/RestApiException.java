package com.baktas.reportingapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RestApiException extends RuntimeException {
    public RestApiException(String message) {
        super(message);
    }
}