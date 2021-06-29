package com.celeste.internal.protocol.codec;

import com.celeste.internal.controllers.ChannelController;
import com.celeste.internal.protocol.utils.Compression;
import com.celeste.internal.protocol.utils.ProtocolBuffer;
import com.celeste.library.core.util.Logger;
import com.google.common.flogger.FluentLogger;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class MessageReader extends ByteToMessageDecoder {

  private final ChannelController controller;

  @Override
  protected void decode(final ChannelHandlerContext context, final ByteBuf byteBuf, final List<Object> list) {
    byteBuf.markReaderIndex();

    final ProtocolBuffer buffer = new ProtocolBuffer(byteBuf);
    if (!controller.isCompression()) {
      if (!buffer.isVarInt()) {
        return;
      }

      final int length = buffer.readVarInt();
      if (byteBuf.readableBytes() > length) {
        list.add(byteBuf.readBytes(length));
        return;
      }

      byteBuf.resetReaderIndex();
      return;
    }

    final byte[] data = Compression.decompress(byteBuf.array());
    final int decompressedId = buffer.readVarInt(data);
    // TODO: Handle decompression and flush with decompressed information for the Decoder
  }

  @Override
  public void exceptionCaught(final ChannelHandlerContext context, final Throwable cause) {
    Logger.getLogger().atWarning().log("A exception was caught at the MessageConvert");
    cause.printStackTrace();
  }

}
