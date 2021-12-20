package com.celeste.internal.exception.protocol;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class PacketException extends RuntimeException {

  public PacketException(final String message) {
    super(message);
  }

  public PacketException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public PacketException(final Throwable cause) {
    super(cause);
  }

}
