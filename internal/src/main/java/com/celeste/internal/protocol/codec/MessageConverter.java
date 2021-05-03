package com.celeste.internal.protocol.codec;

import com.celeste.internal.protocol.util.ProtocolBuffer;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

public final class MessageConverter extends ByteToMessageDecoder {

  @Override
  protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
    final ProtocolBuffer buffer = new ProtocolBuffer(byteBuf);
    if (!buffer.isVarInt()) return;

    final int length = buffer.readVarInt();

    list.add(byteBuf.readBytes(length));
  }

  @Override
  public void exceptionCaught(final ChannelHandlerContext context, Throwable cause) {
    cause.printStackTrace();
  }

}
