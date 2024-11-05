package com.rspinoni.momserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rspinoni.momserver.model.exception.InvalidDeviceException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(InvalidDeviceException.class)
  public ErrorResponse handleInvalidDeviceException(InvalidDeviceException e) {
    return ErrorResponse.create(e, HttpStatusCode.valueOf(HttpStatus.UNAUTHORIZED.value()), e.getMessage());
  }
}
