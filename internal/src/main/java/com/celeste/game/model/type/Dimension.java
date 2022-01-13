package com.celeste.game.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

/**
 * Represents all Minecraft default dimensions, all have a ID.
 */
@Getter
@AllArgsConstructor
public enum Dimension {

  NETHER(-1),
  OVERWORLD(0),
  END(1);

  private final int data;

  @Nullable
  public static Dimension get(int id) {
    return Arrays.stream(values()).filter(dimension -> dimension.getData() == id)
        .findFirst()
        .orElse(null);
  }

}
