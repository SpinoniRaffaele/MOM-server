package com.rspinoni.momserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rspinoni.momserver.model.exception.InvalidDeviceException;
import com.rspinoni.momserver.model.exception.UnexistentUserException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(InvalidDeviceException.class)
  public ErrorResponse handleInvalidDeviceException(InvalidDeviceException e) {
    return ErrorResponse.create(e, HttpStatusCode.valueOf(HttpStatus.UNAUTHORIZED.value()), e.getMessage());
  }

  @ExceptionHandler(UnexistentUserException.class)
  public ErrorResponse handleUnexistentUserException(UnexistentUserException e) {
    return ErrorResponse.create(e, HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()), e.getMessage());
  }
}
