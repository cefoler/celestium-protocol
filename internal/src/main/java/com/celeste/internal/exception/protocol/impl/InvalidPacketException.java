package com.celeste.internal.exception.protocol.impl;

import com.celeste.internal.exception.protocol.PacketException;

public final class InvalidPacketException extends PacketException {

  public InvalidPacketException() {
  }

  public InvalidPacketException(String message) {
    super(message);
  }

  public InvalidPacketException(String message, Throwable cause) {
    super(message, cause);
  }

}
