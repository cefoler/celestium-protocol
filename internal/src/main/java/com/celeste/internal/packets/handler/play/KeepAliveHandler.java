package com.celeste.internal.packets.handler.play;

import com.celeste.internal.controller.ChannelController;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.packets.PacketHandler;
import com.celeste.internal.packets.messages.play.KeepAliveMessage;
import com.celeste.internal.protocol.ServerBootstrapper;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.TimeUnit;

public final class KeepAliveHandler extends PacketHandler {

  public KeepAliveHandler(final ChannelController controller) {
    super(controller);

    ServerBootstrapper.SCHEDULED.scheduleAtFixedRate(() -> dispatch(new KeepAliveMessage(1)), 0, 30, TimeUnit.SECONDS);
  }

  @Override
  public void read(ChannelHandlerContext context, PacketContent message) {}

}
