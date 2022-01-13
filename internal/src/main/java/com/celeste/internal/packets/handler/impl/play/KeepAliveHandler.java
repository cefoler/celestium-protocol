package com.celeste.internal.packets.handler.impl.play;

import com.celeste.internal.controller.ChannelController;
import com.celeste.internal.packets.messages.PacketMessage;
import com.celeste.internal.packets.handler.AbstractPacketHandler;
import com.celeste.internal.packets.messages.play.KeepAliveMessage;
import com.celeste.internal.protocol.ServerBootstrapper;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.TimeUnit;

public final class KeepAliveHandler extends AbstractPacketHandler {

  public KeepAliveHandler(final ChannelController controller) {
    super(controller);
    // TODO: Work more on this one handler, too basic
    ServerBootstrapper.SCHEDULED.scheduleAtFixedRate(() -> dispatch(new KeepAliveMessage(1)), 0, 30, TimeUnit.SECONDS);
  }

  @Override
  public void read(ChannelHandlerContext context, PacketMessage message) {
  }

}
