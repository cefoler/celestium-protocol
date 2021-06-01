package com.celeste.internal.packets.handlers;

import com.celeste.internal.controller.ChannelController;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.packets.PacketHandler;
import com.celeste.internal.packets.messages.play.KeepAliveMessage;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class KeepAliveHandler extends PacketHandler {

  private final ScheduledExecutorService scheduledExecutorService;

  public KeepAliveHandler(final ChannelController controller) {
    super(controller);
    this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    final KeepAliveMessage messageOne = new KeepAliveMessage(1);
    scheduledExecutorService.scheduleAtFixedRate(() -> dispatch(messageOne), 5, 30, TimeUnit.SECONDS);
  }

  @Override
  public void read(ChannelHandlerContext context, PacketContent message) {
  }

}
