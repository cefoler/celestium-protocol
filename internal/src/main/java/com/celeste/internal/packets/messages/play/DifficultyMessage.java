package com.celeste.internal.packets.messages.play;

import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.registry.type.PlayPackets;
import com.celeste.minecraft.model.type.Difficulty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class DifficultyMessage implements PacketContent {

  private final Difficulty difficulty;
  private final boolean locked;

  @Override
  public int getId() {
    return PlayPackets.JOIN.getOutboundId();
  }

}
