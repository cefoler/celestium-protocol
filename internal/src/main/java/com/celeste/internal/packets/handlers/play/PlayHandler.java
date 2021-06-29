package com.celeste.internal.packets.handlers.play;

import com.celeste.internal.controllers.ChannelController;
import com.celeste.internal.exceptions.protocol.PacketException;
import com.celeste.internal.model.protocol.ConnectionState;
import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.packets.PacketHandler;
import com.celeste.internal.registry.Protocol;
import com.celeste.internal.registry.type.PlayPackets;
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

    switch(message.getId()) {
      // TODO: Handle each packets ID from Play that is inbound
    }
  }

}
