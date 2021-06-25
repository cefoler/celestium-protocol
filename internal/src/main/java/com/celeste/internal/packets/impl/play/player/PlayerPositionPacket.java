package com.celeste.internal.packets.impl.play.player;

import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.play.player.PlayerPositionMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;
import com.celeste.internal.registry.type.PlayPackets;

public final class PlayerPositionPacket extends AbstractPacket<PlayerPositionMessage> {

  public PlayerPositionPacket() {
    super(null, PlayPackets.PLAYER_POSITION_AND_LOOK.getOutboundId());
  }

  @Override
  public Class<PlayerPositionMessage> getMessage() {
    return PlayerPositionMessage.class;
  }

  @Override
  public PlayerPositionMessage read(ProtocolBuffer buffer) {
    return null;
  }

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
