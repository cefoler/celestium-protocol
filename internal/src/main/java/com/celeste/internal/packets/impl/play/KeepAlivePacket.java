package com.celeste.internal.packets.impl.play;

import com.celeste.internal.annotation.PacketInfo;
import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.play.KeepAliveMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;

@PacketInfo(outboundId = 0x10)
public final class KeepAlivePacket extends AbstractPacket<KeepAliveMessage> {

  @Override
  public void write(final ProtocolBuffer buffer, final KeepAliveMessage packet) {
    buffer.writeVarLong(packet.getId());
  }

}
