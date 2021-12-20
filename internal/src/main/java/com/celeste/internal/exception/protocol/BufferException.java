package com.celeste.internal.exception.protocol;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class BufferException extends RuntimeException {

  public BufferException(final String message) {
    super(message);
  }

  public BufferException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public BufferException(final Throwable cause) {
    super(cause);
  }


}
