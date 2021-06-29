package com.celeste.internal.packets.impl.handshake;

import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.status.StatusPingMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;

public final class StatusPingPacket extends AbstractPacket<StatusPingMessage> {

  public StatusPingPacket() {
    super(0x01, 0x01);
  }

  @Override
  public Class<StatusPingMessage> getMessage() {
    return StatusPingMessage.class;
  }

  @Override
  public StatusPingMessage read(ProtocolBuffer buffer) {
    return new StatusPingMessage(buffer.getByteBuf().readLong());
  }

  @Override
  public void write(ProtocolBuffer buffer, StatusPingMessage packet) {
    buffer.getByteBuf().writeLong(packet.getNumber());
  }

}
