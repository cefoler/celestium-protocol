package com.celeste.internal.packets.messages.play.player;

import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.registry.type.PlayPackets;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class HeldSlotChangeMessage implements PacketContent {

  // 0-8
  private final int slot;

  @Override
  public int getId() {
    return PlayPackets.HELD_SLOT_CHANGE.getOutboundId();
  }

}
