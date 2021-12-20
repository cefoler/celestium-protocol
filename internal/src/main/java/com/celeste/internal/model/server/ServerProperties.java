package com.celeste.internal.model.server;

import com.celeste.game.model.type.Difficulty;
import com.celeste.game.model.type.Gamemode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public final class ServerProperties implements Serializable {

  private final boolean hardcore;
  private final Difficulty difficulty;

  private final Gamemode gamemode;

  private final int viewDistance;
  private final boolean debug;

  public ServerProperties() {
    this.hardcore = false;
    this.gamemode = Gamemode.CREATIVE;
    this.difficulty = Difficulty.PEACEFUL;
    this.viewDistance = 10;
    this.debug = false;
  }

}
