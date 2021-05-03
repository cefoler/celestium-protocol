package com.celeste.internal.exception;

public class PacketException extends RuntimeException {

  public PacketException(String message) {
    super(message);
  }

  public PacketException(String message, Throwable cause) {
    super(message, cause);
  }

  public PacketException(Throwable cause) {
    super(cause);
  }

}
