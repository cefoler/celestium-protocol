package com.celeste.internal.packets.impl.play;

import com.celeste.internal.annotation.Packet;
import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.play.JoinMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;

@Packet(outboundId = 0x26)
public final class JoinPacket extends AbstractPacket<JoinMessage> {

  @Override
  public void write(final ProtocolBuffer buffer, final JoinMessage packet) {
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
