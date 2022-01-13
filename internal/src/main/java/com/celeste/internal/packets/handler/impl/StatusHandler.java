package com.celeste.internal.packets.handler.impl;

import com.celeste.internal.controller.ChannelController;
import com.celeste.internal.controller.ServerController;
import com.celeste.internal.model.protocol.state.StatusState;
import com.celeste.internal.model.server.ServerSettings;
import com.celeste.internal.packets.messages.PacketMessage;
import com.celeste.internal.packets.handler.AbstractPacketHandler;
import com.celeste.internal.packets.messages.status.StatusResponseMessage;
import com.celeste.library.core.util.Logger;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class StatusHandler extends AbstractPacketHandler {

  private StatusState statusState;
  private long ping;
  public StatusHandler(final ChannelController controller) {
    super(controller);
    this.statusState = StatusState.REQUEST;
  }

  @Override
  public void read(final ChannelHandlerContext context, final PacketMessage message) {
    switch (statusState) {
      case REQUEST -> {
        System.out.println("STATUS REQUEST");
        final ServerSettings settings = ServerController.SETTINGS;
        final StatusResponseMessage responseMessage = new StatusResponseMessage(
            settings.protocol().getName(),
            settings.protocol().getVersion(),
            settings.maximumPlayers(),
            settings.currentPlayers(),
            settings.onlinePlayers(),
            settings.description(),
            settings.icon()
        );

        dispatch(responseMessage);
        setStatusState(StatusState.PING);
      }
      case PING -> {
        System.out.println("STATUS PING");
        dispatch(message);
        setStatusState(StatusState.PONG);
      }
      default -> Logger.getLogger().atSevere().log("The packet received has a invalid status state. ID: " + message.getId());
    }
  }

}
