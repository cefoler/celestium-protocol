package com.celeste.minecraft.model.world;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public final class WorldBorder implements Serializable {

  private double x, y;
  private double diameter;

  private long speed;
  private int warningBlocks;

  public WorldBorder() {
    this.x = 0;
    this.y = 0;
    this.diameter = 100;
    this.speed = 0;
    this.warningBlocks = 5;
  }

}
