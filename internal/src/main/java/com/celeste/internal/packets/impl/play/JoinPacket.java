package com.celeste.internal.packets.impl.play;

import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.play.JoinMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;
import com.celeste.internal.registry.type.PlayPackets;

public final class JoinPacket extends AbstractPacket<JoinMessage> {

  public JoinPacket() {
    super(null, PlayPackets.JOIN.getOutboundId());
  }

  @Override
  public Class<JoinMessage> getMessage() {
    return JoinMessage.class;
  }

  @Override
  public JoinMessage read(ProtocolBuffer buffer) {
    return null;
  }

  @Override
  public void write(ProtocolBuffer buffer, JoinMessage packet) {
    buffer.writeInt(packet.getEntityId());
    buffer.getByteBuf().writeBoolean(packet.isHardcore());
    buffer.getByteBuf().writeByte(packet.getGamemode().getData());
    buffer.writeInt(packet.getWorld().getDimension().getData());
    buffer.writeVarLong(packet.getWorld().getSeed());
    buffer.writeString(packet.getWorld().getName());
    buffer.writeVarInt(packet.getViewDistance());
    buffer.getByteBuf().writeBoolean(packet.isDebug());
    buffer.getByteBuf().writeBoolean(packet.isRespawnScreen());
  }

}
