package com.celeste.game.model.world;

import com.celeste.game.model.Location;
import com.celeste.game.model.type.Difficulty;
import com.celeste.game.model.type.Dimension;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public record World(String name, long seed, Location spawn,
                    Dimension dimension, Difficulty difficulty,
                    WorldBorder border, long time, long creationDate,
                    boolean defaultWorld) implements Serializable {

  public World() {
    this("default", ThreadLocalRandom.current().nextLong(), new Location(),
        Dimension.OVERWORLD, Difficulty.PEACEFUL, new WorldBorder(),
        6000, System.currentTimeMillis(), true);
  }

}
