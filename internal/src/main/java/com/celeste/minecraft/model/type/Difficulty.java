package com.celeste.minecraft.model.type;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

/**
 * Represents the difficulty of the Minecraft server, each one has a ID.
 */
@Getter
@AllArgsConstructor
public enum Difficulty {

  PEACEFUL(0),
  EASY(1),
  NORMAL(2),
  HARD(3);

  private final int id;

  @Nullable
  public static Difficulty getById(int id) {
    return Arrays.stream(values()).filter(difficulty -> difficulty.getId() == id)
        .findFirst()
        .orElse(null);
  }

}
