package com.celeste.internal.packets.impl.handshake;

import com.celeste.internal.annotation.Packet;
import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.status.StatusPingMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;

@Packet(inboundId = 0x01, outboundId = 0x01)
public final class StatusPingPacket extends AbstractPacket<StatusPingMessage> {

  @Override
  public StatusPingMessage read(final ProtocolBuffer buffer) {
    return new StatusPingMessage(buffer.getByteBuf().readLong());
  }

  @Override
  public void write(final ProtocolBuffer buffer, final StatusPingMessage packet) {
    buffer.getByteBuf().writeLong(packet.number());
  }

}
