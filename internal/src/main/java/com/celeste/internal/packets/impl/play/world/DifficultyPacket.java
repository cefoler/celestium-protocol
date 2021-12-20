package com.celeste.internal.packets.impl.play.world;

import com.celeste.internal.annotation.Packet;
import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.play.world.DifficultyMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;

@Packet(outboundId = 0x0E)
public final class DifficultyPacket extends AbstractPacket<DifficultyMessage> {

  @Override
  public void write(ProtocolBuffer buffer, DifficultyMessage packet) {
    buffer.getByteBuf().writeByte(packet.getDifficulty().getId());
    buffer.getByteBuf().writeBoolean(packet.isLocked());
  }

}
