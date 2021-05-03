package com.celeste.internal.views.handlers;

import com.celeste.internal.controller.ChannelController;
import com.celeste.internal.exception.PacketException;
import com.celeste.internal.model.PacketContent;
import com.celeste.internal.model.PacketHandler;
import com.celeste.internal.model.messages.HandshakeMessage;
import com.celeste.internal.model.protocol.type.ConnectionState;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;

public final class HandshakeHandler extends PacketHandler {

  public HandshakeHandler(final ChannelController controller) {
    super(controller);
  }

  @Override
  public void readPacket(final ChannelHandlerContext context, final PacketContent message) {
    final HandshakeMessage handshake = (HandshakeMessage) message;

    getController().setCreationTime(System.currentTimeMillis());
    getController().setProtocolVersion(handshake.getProtocolVersion());

    switch(handshake.getState()) {
      case STATUS: {
        getController().setState(ConnectionState.STATUS);
        // TODO: Set controller handler as StatusHandler
      }
      case LOGIN: {
        getController().setState(ConnectionState.LOGIN);
        // TODO: Set controller handler as LoginHandler
      }
      default: throw new PacketException("The packet received has a invalid next state.");
    }
  }

}
