package com.rspinoni.momserver.model.exception;

public class UnexistentUserException extends RuntimeException {
  public UnexistentUserException(String message) {
    super(message);
  }
}
