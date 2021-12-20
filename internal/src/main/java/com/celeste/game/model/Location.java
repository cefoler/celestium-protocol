package com.celeste.game.model;

import java.io.Serializable;

public record Location(float x, float y, float z, float yaw, float pitch) implements Serializable {

  public Location() {
    this(0, 0, 0, 0, 0);
  }

}
