package com.celeste.internal.packets.handler;

import com.celeste.internal.packets.messages.PacketMessage;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;

public interface PacketHandler {

  void read(final ChannelHandlerContext context, final PacketMessage message);

  void dispatch(final PacketMessage... packets);

}
