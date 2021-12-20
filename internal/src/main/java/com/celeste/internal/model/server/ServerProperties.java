package com.celeste.internal.model.server;

import com.celeste.game.model.type.Difficulty;
import com.celeste.game.model.type.Gamemode;

import java.io.Serializable;

public record ServerProperties(boolean hardcore, Difficulty difficulty, Gamemode gamemode,
                               int viewDistance, boolean debug) implements Serializable {

  public ServerProperties() {
    this(false, Difficulty.PEACEFUL, Gamemode.CREATIVE, 10, false);
  }

}
