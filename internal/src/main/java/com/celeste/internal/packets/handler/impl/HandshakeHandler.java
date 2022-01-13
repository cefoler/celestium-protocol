package com.celeste.internal.packets.handler.impl;

import com.celeste.internal.controller.ChannelController;
import com.celeste.internal.exception.protocol.PacketException;
import com.celeste.internal.model.protocol.ConnectionState;
import com.celeste.internal.model.protocol.ProtocolVersion;
import com.celeste.internal.packets.messages.PacketMessage;
import com.celeste.internal.packets.handler.AbstractPacketHandler;
import com.celeste.internal.packets.messages.handshake.HandshakeMessage;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;

public final class HandshakeHandler extends AbstractPacketHandler {

  public HandshakeHandler(final ChannelController controller) {
    super(controller);
  }

  @Override
  public void read(final ChannelHandlerContext context, final PacketMessage message) {
    System.out.println("HANDSHAKE HANDLER");
    final HandshakeMessage handshake = (HandshakeMessage) message;

    getController().setProtocolVersion(ProtocolVersion.get(handshake.protocolVersion()));

    switch (handshake.state()) {
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
      default -> throw new PacketException("The packet received has a invalid next state. ID: " + message.getId());
    }
  }

}
