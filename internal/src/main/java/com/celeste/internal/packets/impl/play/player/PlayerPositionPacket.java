package com.celeste.internal.packets.impl.play.player;

import com.celeste.internal.annotation.Packet;
import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.play.player.PlayerPositionMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;

@Packet(outboundId = 0x38)
public final class PlayerPositionPacket extends AbstractPacket<PlayerPositionMessage> {

  @Override
  public void write(ProtocolBuffer buffer, PlayerPositionMessage packet) {
    buffer.getByteBuf().writeDouble(packet.getX());
    buffer.getByteBuf().writeDouble(packet.getY());
    buffer.getByteBuf().writeDouble(packet.getZ());

    buffer.getByteBuf().writeFloat(packet.getYaw());
    buffer.getByteBuf().writeFloat(packet.getPitch());

    buffer.getByteBuf().writeByte(packet.getFlag());
    buffer.writeVarInt(packet.getTeleportId());
    buffer.getByteBuf().writeBoolean(packet.isDismount());
  }

}
