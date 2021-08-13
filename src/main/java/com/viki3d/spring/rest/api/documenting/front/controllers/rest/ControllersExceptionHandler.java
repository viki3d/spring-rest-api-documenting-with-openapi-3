package com.viki3d.spring.rest.api.documenting.front.controllers.rest;

import com.viki3d.spring.rest.api.documenting.logic.services.exceptions.CarNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * {@code @RestControllerAdvice} is handling exceptions from all REST API controllers. We use this 
 * approach to avoid handling exceptions at each method level.
 */
@RestControllerAdvice
public class ControllersExceptionHandler {
  
  @ExceptionHandler(CarNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handleConnversion(RuntimeException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

}
