package com.celeste.internal.packets.impl.play;

import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.play.KeepAliveMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;
import com.celeste.internal.registry.type.PlayPackets;

public final class KeepAlivePacket extends AbstractPacket<KeepAliveMessage> {

  public KeepAlivePacket() {
    super(null, PlayPackets.KEEP_ALIVE.getOutboundId());
  }

  @Override
  public Class<KeepAliveMessage> getMessage() {
    return KeepAliveMessage.class;
  }

  @Override
  public KeepAliveMessage read(ProtocolBuffer buffer) {
    return null;
  }

  @Override
  public void write(ProtocolBuffer buffer, KeepAliveMessage packet) {
    buffer.writeVarLong(packet.getId());
  }

}
