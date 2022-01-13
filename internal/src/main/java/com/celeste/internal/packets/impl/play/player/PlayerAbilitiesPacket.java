package com.celeste.internal.packets.impl.play.player;

import com.celeste.internal.annotation.PacketInfo;
import com.celeste.internal.model.client.type.Flags;
import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.play.player.PlayerAbilitiesMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;

@PacketInfo(inboundId = 0x32, outboundId = 0x32)
public final class PlayerAbilitiesPacket extends AbstractPacket<PlayerAbilitiesMessage> {

  @Override
  public PlayerAbilitiesMessage read(final ProtocolBuffer buffer) {
    return new PlayerAbilitiesMessage(
        Flags.get(buffer.getByteBuf().readByte()),
        buffer.getByteBuf().readFloat(),
        buffer.getByteBuf().readFloat()
    );
  }

  @Override
  public void write(final ProtocolBuffer buffer, final PlayerAbilitiesMessage packet) {
    buffer.getByteBuf().writeByte(packet.flags().getId());
    buffer.getByteBuf().writeFloat(packet.flyingSpeed());
    buffer.getByteBuf().writeFloat(packet.viewModifier());
  }

}
