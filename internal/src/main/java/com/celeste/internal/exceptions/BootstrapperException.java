package com.celeste.internal.exceptions;

public class BootstrapperException extends RuntimeException {

  public BootstrapperException(String message) {
    super(message);
  }

  public BootstrapperException(String message, Throwable cause) {
    super(message, cause);
  }

  public BootstrapperException(Throwable cause) {
    super(cause);
  }

}
