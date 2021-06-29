package com.celeste.internal.packets.handlers;

import com.celeste.internal.controllers.ChannelController;
import com.celeste.internal.controllers.ServerController;
import com.celeste.internal.exceptions.protocol.PacketException;
import com.celeste.internal.model.protocol.ConnectionState;
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
    System.out.println("HANDSHAKE HANDLER");
    final HandshakeMessage handshake = (HandshakeMessage) message;

    getController().setCreationTime(System.currentTimeMillis());
    getController().setProtocolVersion(ServerController.SETTINGS.getProtocol());

    switch (handshake.getState()) {
      case STATUS -> {
        System.out.println("STATUS");
        getController().setHandler(new StatusHandler(getController()));
        getController().setState(ConnectionState.STATUS);
      }
      case LOGIN -> {
        System.out.println("LOGIN");
        getController().setHandler(new LoginHandler(getController()));
        getController().setState(ConnectionState.LOGIN);
      }
      default -> throw new PacketException("The packet received has a invalid next state.");
    }
  }

}
