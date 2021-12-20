package com.celeste.internal.packets.messages.play.world;

import com.celeste.internal.controller.ServerController;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.registry.type.PlayPackets;
import com.celeste.game.model.type.Difficulty;
import lombok.Data;

@Data
public final class DifficultyMessage implements PacketContent {

  private final Difficulty difficulty;
  private final boolean locked;

  @Override
  public int getId() {
    return PlayPackets.JOIN.getOutboundId();
  }

  public DifficultyMessage() {
    this.difficulty = ServerController.PROPERTIES.getDifficulty();
    this.locked = false;
  }

}
