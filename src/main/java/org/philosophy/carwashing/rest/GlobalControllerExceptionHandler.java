package org.philosophy.carwashing.rest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

import static org.philosophy.carwashing.util.CommonStringConstants.ENTITY_NOT_FOUND_MESSAGE;
import static org.philosophy.carwashing.util.CommonStringConstants.WRONG_PARAMETERS_MESSAGE;

@ControllerAdvice
class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {EntityNotFoundException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<String> handleEntityNotFound(Exception e) {
    return ResponseEntity.badRequest().body(ENTITY_NOT_FOUND_MESSAGE + e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(Exception e) {
        return ResponseEntity.badRequest().body(WRONG_PARAMETERS_MESSAGE + e.getMessage());
    }

}
