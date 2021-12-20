package com.celeste.internal.protocol.utils;

import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public final class Compression {

  public static byte[] compress(byte[] data) throws IOException {
    final Deflater deflater = new Deflater();

    deflater.setInput(data);
    deflater.setLevel(Deflater.BEST_SPEED);
    deflater.finish();

    final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

    final byte[] buffer = new byte[256];
    while (!deflater.finished()) {
      outputStream.write(buffer, 0, deflater.deflate(buffer));
    }

    outputStream.close();
    return outputStream.toByteArray();
  }

  @SneakyThrows
  public static byte[] decompress(byte[] data) {
    final Inflater inflater = new Inflater();
    inflater.setInput(data);

    final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

    final byte[] buffer = new byte[256];
    while (!inflater.finished()) {
      outputStream.write(buffer, 0, inflater.inflate(buffer));
    }

    outputStream.close();
    return outputStream.toByteArray();
  }

}
