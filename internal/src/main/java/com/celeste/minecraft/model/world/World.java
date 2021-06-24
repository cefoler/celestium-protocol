package com.celeste.minecraft.model.world;

import com.celeste.minecraft.model.Location;
import com.celeste.minecraft.model.type.Difficulty;
import com.celeste.minecraft.model.type.Dimension;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Random;

@Data
@Builder
public final class World implements Serializable {

  private final String name;
  private final long seed;

  private Location spawn;

  private Dimension dimension;
  private Difficulty difficulty;

  private WorldBorder border;

  private long time;
  private long creationDate;

  public World() {
    this.name = "default";
    this.seed = new Random().nextLong();
    this.spawn = new Location();
    this.dimension = Dimension.OVERWORLD;
    this.difficulty = Difficulty.PEACEFUL;
    this.border = new WorldBorder();
    this.time = 6000;
    this.creationDate = System.currentTimeMillis();
  }

}
