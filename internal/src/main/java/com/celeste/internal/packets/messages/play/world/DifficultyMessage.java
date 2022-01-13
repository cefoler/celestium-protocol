package com.celeste.internal.packets.messages.play.world;

import com.celeste.game.model.type.Difficulty;
import com.celeste.internal.annotation.Message;
import com.celeste.internal.controller.ServerController;
import com.celeste.internal.packets.messages.PacketMessage;

@Message(id = 0x26)
public record DifficultyMessage(Difficulty difficulty, boolean locked) implements PacketMessage {

  public DifficultyMessage() {
    this(ServerController.PROPERTIES.difficulty(), false);
  }

}
