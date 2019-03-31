package com.baktas.reportingapi.entities;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IResponseEntity {
    static <X> ResponseEntity<X> getResponseEntity(Optional<X> maybeResponse) {
        return getResponseEntity(maybeResponse, (HttpHeaders)null);
    }

    static <X> ResponseEntity<X> getResponseEntity(Optional<X> maybeResponse, HttpHeaders header) {
        return (ResponseEntity)maybeResponse.map((response) -> {
            return (ResponseEntity.ok().headers(header)).body(response);
        }).orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
}