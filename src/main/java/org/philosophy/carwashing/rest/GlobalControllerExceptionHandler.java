package org.philosophy.carwashing.rest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EntityNotFoundException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<String> handleEntityNotFound() {
    return ResponseEntity.badRequest().body("Сущности с таким id нет в базе данных");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(Exception e) {
        return ResponseEntity.badRequest().body("Неверные параметры. " + e.getMessage());
    }

}
