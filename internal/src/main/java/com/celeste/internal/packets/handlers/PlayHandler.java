package com.celeste.internal.packets.handlers;

import com.celeste.internal.controller.ChannelController;
import com.celeste.internal.exceptions.PacketException;
import com.celeste.internal.model.type.ConnectionState;
import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.packets.PacketHandler;
import com.celeste.internal.registry.Protocol;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PlayHandler extends PacketHandler {

  public PlayHandler(final ChannelController controller) {
    super(controller);
  }

  @Override
  public void read(final ChannelHandlerContext context, final PacketContent message) {
    final AbstractPacket<?> packet = Protocol.INSTANCE.getPacketInbound(ConnectionState.PLAY, message.getId());
    if (packet == null) {
      throw new PacketException();
    }

    // TODO:
  }

}
