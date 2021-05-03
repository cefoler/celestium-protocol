package com.celeste.internal.model;

import com.celeste.internal.controller.ChannelController;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class PacketHandler {

  private final ChannelController controller;

  public abstract void readPacket(final ChannelHandlerContext context, final PacketContent message);

}
