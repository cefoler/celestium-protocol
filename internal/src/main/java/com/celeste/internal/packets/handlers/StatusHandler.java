package com.celeste.internal.packets.handlers;

import com.celeste.internal.controllers.ChannelController;
import com.celeste.internal.controllers.ServerController;
import com.celeste.internal.exceptions.protocol.PacketException;
import com.celeste.internal.model.server.ServerSettings;
import com.celeste.internal.model.protocol.state.StatusState;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.packets.PacketHandler;
import com.celeste.internal.packets.messages.status.StatusResponseMessage;
import com.celeste.library.core.adapter.impl.jackson.JacksonAdapter;
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
        System.out.println("STATUS PING");
        dispatch(message);
        setStatusState(StatusState.PONG);
      }
      default -> Logger.getLogger().atSevere().log("The packet received has a invalid status state.");
    }
  }

}
