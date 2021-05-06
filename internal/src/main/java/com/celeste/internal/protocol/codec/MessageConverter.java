package com.celeste.internal.protocol.codec;

import com.celeste.internal.controller.ChannelController;
import com.celeste.internal.model.type.ConnectionState;
import com.celeste.internal.protocol.util.ProtocolBuffer;
import com.google.common.flogger.FluentLogger;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class MessageConverter extends ByteToMessageDecoder {

  private final FluentLogger logger = FluentLogger.forEnclosingClass();
  private final ChannelController controller;

  @Override
  protected void decode(final ChannelHandlerContext context, final ByteBuf byteBuf, final List<Object> list) {
    byteBuf.markReaderIndex();

    final ProtocolBuffer buffer = new ProtocolBuffer(byteBuf);
    if (!buffer.isVarInt()) return;

    final int length = buffer.readVarInt();
    if (byteBuf.readableBytes() > length) {
      list.add(byteBuf.readBytes(length));
      return;
    }

    byteBuf.resetReaderIndex();
    if (controller.getState() == ConnectionState.HANDSHAKE && byteBuf.readUnsignedByte() == 254) {
      // TODO: Add support for Legacy Server List Ping
      context.close();
    }

    byteBuf.resetReaderIndex();
  }

  @Override
  public void exceptionCaught(final ChannelHandlerContext context, final Throwable cause) {
    logger.atWarning().log("A exception was caught at the MessageConvert");
    cause.printStackTrace();
  }

}
