package com.celeste.internal.model.client.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Flags {

  INVULNERABLE(0x01),
  FLYING(0x02),
  ALLOW_FLYING(0x04),
  CREATIVE_MODE(0x08);

  private final int id;

  @Nullable
  public static Flags get(final int id) {
    return Arrays.stream(values())
        .filter(flags -> flags.getId() == id)
        .findFirst()
        .orElse(null);
  }

  public static Flags getOrElse(final int id, final Flags flag) {
    return Arrays.stream(values())
        .filter(flags -> flags.getId() == id)
        .findFirst()
        .orElse(flag);
  }

}
