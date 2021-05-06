package com.celeste.internal.protocol.codec;

import com.celeste.internal.protocol.util.ProtocolBuffer;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder;

public final class MessageWriter extends MessageToByteEncoder<ByteBuf> {

  @Override
  protected void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf output) throws Exception {
    final ProtocolBuffer buffer = new ProtocolBuffer(byteBuf);

    buffer.writeVarInt(byteBuf.readableBytes());
    output.writeBytes(byteBuf);
  }

}
