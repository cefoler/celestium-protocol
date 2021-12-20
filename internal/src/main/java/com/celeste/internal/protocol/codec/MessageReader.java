package com.celeste.internal.protocol.codec;

import com.celeste.internal.controller.ChannelController;
import com.celeste.internal.model.protocol.ConnectionState;
import com.celeste.internal.protocol.utils.Compression;
import com.celeste.internal.protocol.utils.ProtocolBuffer;
import com.celeste.library.core.util.Logger;
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
    if (!buffer.isVarInt()) {
      return;
    }

    if (!controller.isCompression()) {
      final int length = buffer.readVarInt();
      if (byteBuf.readableBytes() > length) {
        list.add(byteBuf.readBytes(length));
        return;
      }

      if (controller.getState() == ConnectionState.HANDSHAKE && byteBuf.readUnsignedByte() == 254) {
        Logger.getLogger().atInfo().log("TODO: Implement legacy server list ping");
        context.close();
      }

      byteBuf.resetReaderIndex();
      return;
    }

    final byte[] data = Compression.decompress(byteBuf.array());
    final int length = buffer.readVarInt(data);
    // TODO: Handle decompression and flush with decompressed information for the Decoder
  }

  @Override
  public void exceptionCaught(final ChannelHandlerContext context, final Throwable cause) {
    Logger.getLogger().atWarning().log("A exception was caught at the MessageConvert");
    cause.printStackTrace();
  }

}
