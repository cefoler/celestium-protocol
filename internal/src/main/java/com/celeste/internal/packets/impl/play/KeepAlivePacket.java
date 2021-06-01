package com.celeste.internal.packets.impl.play;

import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.play.KeepAliveMessage;
import com.celeste.internal.protocol.util.ProtocolBuffer;

public final class KeepAlivePacket extends AbstractPacket<KeepAliveMessage> {

  public KeepAlivePacket() {
    super(null, 0x10);
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
