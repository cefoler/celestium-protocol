package com.celeste.internal.packets;

import com.celeste.internal.controller.ChannelController;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class PacketHandler {

  private final ChannelController controller;

  public abstract void read(final ChannelHandlerContext context, final PacketContent message);

}
