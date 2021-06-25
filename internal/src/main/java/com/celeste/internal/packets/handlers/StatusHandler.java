package com.celeste.internal.packets.handlers;

import com.celeste.internal.controllers.ChannelController;
import com.celeste.internal.controllers.ServerController;
import com.celeste.internal.exceptions.PacketException;
import com.celeste.internal.model.server.ServerSettings;
import com.celeste.internal.model.type.StatusState;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.packets.PacketHandler;
import com.celeste.internal.packets.messages.status.StatusResponseMessage;
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
        final ServerSettings settings = ServerController.SETTINGS;
        final StatusResponseMessage responseMessage = StatusResponseMessage.builder()
            .versionName(settings.getProtocol().getName())
            .protocol(settings.getProtocol().getVersion())
            .currentPlayers(settings.getCurrentPlayers())
            .maximumPlayers(settings.getMaximumPlayers())
            .description(settings.getDescription())
            .onlinePlayers(settings.getOnlinePlayers())
            .icon(settings.getIcon())
            .build();

        dispatch(responseMessage);
        setStatusState(StatusState.PING);
      }
      case PING -> {
        dispatch(message);
        setStatusState(StatusState.PONG);
      }
      default -> throw new PacketException("The packet received has a invalid status state.");
    }
  }

}
