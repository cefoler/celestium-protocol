package com.celeste.internal.packets.handlers;

import com.celeste.internal.controllers.ChannelController;
import com.celeste.internal.exceptions.PacketException;
import com.celeste.internal.model.type.ConnectionState;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.packets.PacketHandler;
import com.celeste.internal.packets.messages.HandshakeMessage;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;

public final class HandshakeHandler extends PacketHandler {

  public HandshakeHandler(final ChannelController controller) {
    super(controller);
  }

  @Override
  public void read(final ChannelHandlerContext context, final PacketContent message) {
    final HandshakeMessage handshake = (HandshakeMessage) message;

    getController().setCreationTime(System.currentTimeMillis());
    getController().setProtocolVersion(handshake.getProtocolVersion());

    switch (handshake.getState()) {
      case STATUS -> {
        getController().setState(ConnectionState.STATUS);

        final StatusHandler handler = new StatusHandler(getController());
        getController().setHandler(handler);

        handler.read(context, message);
      }
      case LOGIN -> {
        getController().setState(ConnectionState.LOGIN);

        final LoginHandler handler = new LoginHandler(getController());
        getController().setHandler(handler);

        handler.read(context, message);
      }
      default -> throw new PacketException("The packet received has a invalid next state.");
    }
  }

}
