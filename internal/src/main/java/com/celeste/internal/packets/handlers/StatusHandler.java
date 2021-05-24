package com.celeste.internal.packets.handlers;

import com.celeste.internal.controller.ChannelController;
import com.celeste.internal.exception.PacketException;
import com.celeste.internal.model.type.StatusState;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.packets.PacketHandler;
import com.celeste.internal.packets.messages.status.StatusResponseMessage;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

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
      case REQUEST: {
        final StatusResponseMessage responseMessage = StatusResponseMessage.builder()
            .versionName("1.8.8")
            .protocol(47)
            .currentPlayers(0)
            .maximumPlayers(100)
            .description("Minha motd")
            .onlinePlayers(new ArrayList<>())
            .icon("")
            .build();

        dispatch(responseMessage);
        setStatusState(StatusState.PING);
        break;
      }
      case PING: {
        dispatch(message);
        setStatusState(StatusState.PONG);
        break;
      }
      default: throw new PacketException("The packet received has a invalid status state.");
    }
  }

}
