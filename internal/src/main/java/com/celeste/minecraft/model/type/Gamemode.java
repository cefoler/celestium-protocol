package com.celeste.minecraft.model.type;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@Getter
@AllArgsConstructor
public enum Gamemode {

  SURVIVAL(0),
  CREATIVE(1),
  ADVENTURE(2),
  SPECTATOR(3);

  private final int data;

  @Nullable
  public static Gamemode getById(int id) {
    return Arrays.stream(values())
        .filter(gamemode -> gamemode.getData() == id)
        .findFirst()
        .orElse(null);
  }

}
