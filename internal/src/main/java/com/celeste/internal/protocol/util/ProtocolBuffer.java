package com.celeste.internal.protocol.util;

import com.celeste.internal.exception.BufferException;
import com.google.common.base.Charsets;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class ProtocolBuffer {

  private final ByteBuf byteBuf;

  /**
   * Reads a VarInt through the ByteBuf
   * @return Integer
   */
  public int readVarInt() {
    int totalBytes = 0;

    int result = 0;
    byte next;
    do {
      if (totalBytes > 4) throw new BufferException("VarInt is longer than the allowed 5 bytes");

      next = byteBuf.readByte();
      result |= (next & 0x7F) << (totalBytes * 7);

      totalBytes++;
    } while((next & 0x80) != 0);

    return result;
  }

  /**
   * Reads a VarLong through the ByteBuf
   * @return Long
   */
  public long readVarLong() {
    int totalBytes = 0;

    long result = 0;
    byte next;
    do {
      if (totalBytes > 9) throw new BufferException("VarLong is longer than the allowed 10 bytes");

      next = byteBuf.readByte();
      result |= (long) (next & 0x7F) << (totalBytes * 7);

      totalBytes++;
    } while((next & 0x80) != 0);

    return result;
  }

  /**
   * Transforms the Integer value into a VarInt
   * and writes in the ByteBuf
   * @param value Integer
   */
  public void writeVarInt(int value) {
    while ((value & -128) != 0) {
      byteBuf.writeByte(value & 127 | 128);
      value >>>= 7;
    }

    byteBuf.writeByte(value);
  }

  /**
   * Transforms the Long value into a VarInt
   * and writes in the ByteBuf
   * @param value Long
   */
  public void writeVarLong(long value) {
    while ((value & -128L) != 0L) {
      byteBuf.writeByte((int) (value & 127L) | 128);
      value >>>= 7;
    }

    byteBuf.writeByte((int) value);
  }

  /**
   * Write the int value into the byteBuf
   * @param value Integer
   */
  public void writeInt(int value) {
    while ((value & -128) != 0) {
      byteBuf.writeByte(value & 127 | 128);
      value >>>= 7;
    }

    byteBuf.writeByte(value);
  }

  public boolean isVarInt() {
    if (byteBuf.readableBytes() > 5) return true;
    int totalBytes = 0;

    byte next;
    do {
      if (totalBytes > 4) break;

      if (byteBuf.readableBytes() <= 0) {
        byteBuf.resetReaderIndex();
        return false;
      }

      next = byteBuf.readByte();
      totalBytes++;
    } while((next & 0x80) != 0);

    byteBuf.resetReaderIndex();
    return true;
  }

  /**
   * Get a UUID through the ByteBuf
   * @return UUID
   */
  public UUID getUUID() {
    return new UUID(byteBuf.readLong(), byteBuf.readLong());
  }

  /**
   * Transforms a UUID into byte and
   * writes it in the ByteBuf
   * @param id UUID
   */
  public void writeUUID(final UUID id) {
    byteBuf.writeLong(id.getMostSignificantBits());
    byteBuf.writeLong(id.getLeastSignificantBits());
  }

  /**
   * Reads a String from the ByteBuf
   * @param input int
   *
   * @return String
   */
  public String readString(int input) {
    final int length = readVarInt();
    if (length >= Short.MAX_VALUE) throw new BufferException("The received string is longer than maximum (" + Short.MAX_VALUE + ")");

    byte[] bytes = new byte[length];
    byteBuf.readBytes(bytes);

    final String string = new String(bytes, Charsets.UTF_8);
    if (string.length() > input) throw new BufferException("The received string is longer than allowed (" + input + ")");

    return string;
  }

  /**
   * Reads a String from the ByteBuf
   * @return String
   */
  public String readString() {
    final int length = readVarInt();
    if (length >= Short.MAX_VALUE) throw new BufferException("The received string is longer than maximum (" + Short.MAX_VALUE + ")");

    return new String(byteBuf.readBytes(length).array(), Charsets.UTF_8);
  }

  /**
   * Writes a String into the ByteBuf in the
   * UTF-8 Charset
   * @param string String
   */
  public void writeString(final String string) {
    byte[] bytes = string.getBytes(Charsets.UTF_8);

    if (bytes.length > Short.MAX_VALUE) {
      throw new BufferException("String is longer than allowed (" + string.length() + ")");
    }

    writeInt(bytes.length);
    byteBuf.writeBytes(bytes);
  }

}
