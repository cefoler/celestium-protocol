package com.celeste.internal.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class BootstrapperException extends RuntimeException {

  public BootstrapperException(final String message) {
    super(message);
  }

  public BootstrapperException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public BootstrapperException(final Throwable cause) {
    super(cause);
  }

}
