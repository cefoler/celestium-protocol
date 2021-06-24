package com.celeste.internal.exceptions;

public class PacketException extends RuntimeException {

  public PacketException() {
  }

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
