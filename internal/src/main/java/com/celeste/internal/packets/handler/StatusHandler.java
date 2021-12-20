package com.celeste.internal.packets.handler;

import com.celeste.internal.controller.ChannelController;
import com.celeste.internal.controller.ServerController;
import com.celeste.internal.model.server.ServerSettings;
import com.celeste.internal.model.protocol.state.StatusState;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.packets.PacketHandler;
import com.celeste.internal.packets.messages.status.StatusResponseMessage;
import com.celeste.library.core.util.Logger;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class StatusHandler extends PacketHandler {

  public StatusHandler(final ChannelController controller) {
    super(controller);
    this.statusState = StatusState.REQUEST;
  }

  private StatusState statusState;
  private long ping;

  @Override
  public void read(final ChannelHandlerContext context, final PacketContent message) {
    switch (statusState) {
      case REQUEST -> {
        System.out.println("STATUS REQUEST");
        final ServerSettings settings = ServerController.SETTINGS;
        final StatusResponseMessage responseMessage = new StatusResponseMessage(
            settings.getProtocol().getName(),
            settings.getProtocol().getVersion(),
            settings.getMaximumPlayers(),
            settings.getCurrentPlayers(),
            settings.getOnlinePlayers(),
            settings.getDescription(),
            settings.getIcon()
        );

        dispatch(responseMessage);
        setStatusState(StatusState.PING);
      }
      case PING -> {
        System.out.println("STATUS PING");
        dispatch(message);
        setStatusState(StatusState.PONG);
      }
      default -> Logger.getLogger().atSevere().log("The packet received has a invalid status state.");
    }
  }

}
