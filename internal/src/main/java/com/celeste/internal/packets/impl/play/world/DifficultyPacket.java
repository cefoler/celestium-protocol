package com.celeste.internal.packets.impl.play.world;

import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.play.world.DifficultyMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;
import com.celeste.internal.registry.type.PlayPackets;

public final class DifficultyPacket extends AbstractPacket<DifficultyMessage> {

  public DifficultyPacket() {
    super(null, PlayPackets.DIFFICULTY.getOutboundId());
  }

  @Override
  public Class<DifficultyMessage> getMessage() {
    return DifficultyMessage.class;
  }

  @Override
  public DifficultyMessage read(ProtocolBuffer buffer) {
    return null;
  }

  @Override
  public void write(ProtocolBuffer buffer, DifficultyMessage packet) {
    buffer.getByteBuf().writeByte(packet.getDifficulty().getId());
    buffer.getByteBuf().writeBoolean(packet.isLocked());
  }

}
