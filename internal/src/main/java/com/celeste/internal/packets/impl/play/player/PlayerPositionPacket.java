package com.celeste.internal.packets.impl.play.player;

import com.celeste.internal.annotation.Packet;
import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.play.player.PlayerPositionMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;

@Packet(outboundId = 0x38)
public final class PlayerPositionPacket extends AbstractPacket<PlayerPositionMessage> {

  @Override
  public void write(final ProtocolBuffer buffer, final PlayerPositionMessage packet) {
    buffer.getByteBuf().writeDouble(packet.x());
    buffer.getByteBuf().writeDouble(packet.y());
    buffer.getByteBuf().writeDouble(packet.z());

    buffer.getByteBuf().writeFloat(packet.yaw());
    buffer.getByteBuf().writeFloat(packet.pitch());

    buffer.getByteBuf().writeByte(packet.flag());
    buffer.writeVarInt(packet.teleportId());
    buffer.getByteBuf().writeBoolean(packet.dismount());
  }

}
