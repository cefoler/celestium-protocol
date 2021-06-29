package com.celeste.internal.packets.messages.play.player;

import com.celeste.internal.model.client.type.Flags;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.registry.type.PlayPackets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public final class PlayerAbilitiesMessage implements PacketContent {

  private final Flags flags;
  private final float flyingSpeed, viewModifier;

  public PlayerAbilitiesMessage() {
    this.flags = Flags.ALLOW_FLYING;
    this.flyingSpeed = (float) 0.05;
    this.viewModifier = (float) 0.1;
  }

  @Override
  public int getId() {
    return PlayPackets.PLAYER_ABILITIES.getOutboundId();
  }

}
