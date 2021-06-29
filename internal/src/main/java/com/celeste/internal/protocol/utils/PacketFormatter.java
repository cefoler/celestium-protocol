package com.celeste.internal.protocol.utils;

public final class PacketFormatter {

  public static String format(final int packetId) {
    return String.format("0x%08x", packetId);
  }

}
