package com.celeste.internal.protocol.utils;

import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public final class Compression {

  @SneakyThrows
  public static byte[] compress(final byte[] data) {
    final Deflater deflater = new Deflater();

    deflater.setInput(data);
    deflater.setLevel(Deflater.BEST_SPEED);
    deflater.finish();

    try (final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length)) {
      final byte[] buffer = new byte[256];
      while (!deflater.finished()) {
        outputStream.write(buffer, 0, deflater.deflate(buffer));
      }

      return outputStream.toByteArray();
    }
  }

  @SneakyThrows
  public static byte[] decompress(final byte[] data) {
    final Inflater inflater = new Inflater();
    inflater.setInput(data);

    try (final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length)) {
      final byte[] buffer = new byte[256];
      while (!inflater.finished()) {
        outputStream.write(buffer, 0, inflater.inflate(buffer));
      }

      return outputStream.toByteArray();
    }
  }

}
