package com.form3.coding.exercise.paymentapi.exception;

import java.util.UUID;

/**
 * Created by Attila on 14/01/2018.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super(String.format("Resource not found: %s", id));
    }
}
