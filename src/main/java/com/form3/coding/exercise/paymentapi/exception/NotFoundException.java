package com.form3.coding.exercise.paymentapi.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Created by Attila on 14/01/2018.
 */
@ResponseStatus(NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super(String.format("Resource not found: %s", id));
    }
}
