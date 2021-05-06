package com.celeste.internal.packets.handlers;

import com.celeste.internal.controller.ChannelController;
import com.celeste.internal.model.type.ConnectionState;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.packets.PacketHandler;
import com.celeste.internal.packets.messages.LoginStartMessage;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;

public final class LoginStartHandler extends PacketHandler {

  public LoginStartHandler(final ChannelController controller) {
    super(controller);
  }

  @Override
  public void read(final ChannelHandlerContext context, final PacketContent message) {
    final LoginStartMessage handshake = (LoginStartMessage) message;
    getController().setState(ConnectionState.LOGIN);
  }

}
