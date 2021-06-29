package com.celeste.internal.packets.impl.play.player;

import com.celeste.internal.model.client.type.Flags;
import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.play.player.PlayerAbilitiesMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;
import com.celeste.internal.registry.type.PlayPackets;

public final class PlayerAbilitiesPacket extends AbstractPacket<PlayerAbilitiesMessage> {

  public PlayerAbilitiesPacket() {
    super(PlayPackets.PLAYER_ABILITIES.getInboundId(), PlayPackets.PLAYER_ABILITIES.getOutboundId());
  }

  @Override
  public Class<PlayerAbilitiesMessage> getMessage() {
    return PlayerAbilitiesMessage.class;
  }

  @Override
  public PlayerAbilitiesMessage read(final ProtocolBuffer buffer) {
    return PlayerAbilitiesMessage.builder()
        .flags(Flags.get(buffer.getByteBuf().readByte()))
        .flyingSpeed(buffer.getByteBuf().readFloat())
        .viewModifier(buffer.getByteBuf().readFloat())
        .build();
  }

  @Override
  public void write(final ProtocolBuffer buffer, final PlayerAbilitiesMessage packet) {
    buffer.getByteBuf().writeByte(packet.getFlags().getId());
    buffer.getByteBuf().writeFloat(packet.getFlyingSpeed());
    buffer.getByteBuf().writeFloat(packet.getViewModifier());
  }

}
