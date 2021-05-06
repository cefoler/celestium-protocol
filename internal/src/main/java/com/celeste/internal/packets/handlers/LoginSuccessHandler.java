package com.celeste.internal.packets.handlers;

import com.celeste.internal.controller.ChannelController;
import com.celeste.internal.model.type.ConnectionState;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.packets.PacketHandler;
import com.celeste.internal.packets.messages.LoginSuccessMessage;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;

public class LoginSuccessHandler extends PacketHandler {

  public LoginSuccessHandler(final ChannelController controller) {
    super(controller);
  }

  @Override
  public void read(ChannelHandlerContext context, PacketContent message) {
    final LoginSuccessMessage loginSuccessMessage = (LoginSuccessMessage) message;

    getController().setState(ConnectionState.PLAY);
  }

}
