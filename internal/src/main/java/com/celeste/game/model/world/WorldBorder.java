package com.celeste.game.model.world;

import java.io.Serializable;

public record WorldBorder(double x, double y, double diameter,
                          long speed, int warningBlocks) implements Serializable {

  public WorldBorder() {
    this(0, 0, 100, 0, 5);
  }

}
