package com.celeste.internal.packets.messages.play.world;

import com.celeste.internal.annotation.Message;
import com.celeste.internal.controller.ServerController;
import com.celeste.internal.packets.PacketContent;
import com.celeste.game.model.type.Difficulty;

@Message(id = 0x26)
public record DifficultyMessage(Difficulty difficulty, boolean locked) implements PacketContent {

  public DifficultyMessage() {
    this(ServerController.PROPERTIES.getDifficulty(), false);
  }

}
