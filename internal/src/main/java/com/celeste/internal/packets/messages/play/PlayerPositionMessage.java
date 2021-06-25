package com.celeste.internal.packets.messages.play;

import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.registry.type.PlayPackets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public final class PlayerPositionMessage implements PacketContent {

  private final double x, y, z;
  private final float yaw, pitch;

  private final int flag;

  private final int teleportId;
  private final boolean dismount;

  @Override
  public int getId() {
    return PlayPackets.PLAYER_POSITION_AND_LOOK.getOutboundId();
  }

}
