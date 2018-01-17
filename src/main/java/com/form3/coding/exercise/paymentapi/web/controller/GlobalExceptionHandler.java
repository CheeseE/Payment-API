package com.form3.coding.exercise.paymentapi.web.controller;

import com.form3.coding.exercise.paymentapi.exception.NotFoundException;
import com.form3.coding.exercise.paymentapi.web.resource.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Attila on 14/01/2018.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> errorHandler(Exception ex, WebRequest request) {
        LOG.error("Internal server error: ", ex);
        return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error.", ""), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> badRequestHandler(IllegalArgumentException ex,  WebRequest request) {
        LOG.error("Bad request: ", ex);
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, "Bad request.", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> notFound(NotFoundException ex,  WebRequest request) {
        LOG.error("Entity Not found: ", ex);
        return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND, "Not Found.", ""), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors =
                ex.getBindingResult().getFieldErrors()
                        .stream()
                        .map(error -> error.getField() + ": " + error.getDefaultMessage())
                        .collect(Collectors.toList());
        errors.addAll(ex.getBindingResult().getGlobalErrors()
                .stream()
                .map(error -> error.getObjectName() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList()));

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }
}
