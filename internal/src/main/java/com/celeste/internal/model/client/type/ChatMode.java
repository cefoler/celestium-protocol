package com.celeste.internal.model.client.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ChatMode {

  ENABLED(0),
  COMMANDS_ONLY(1),
  HIDDEN(2);

  private final int id;

  @Nullable
  public static ChatMode get(final int id) {
    return Arrays.stream(values())
        .filter(chatMode -> chatMode.getId() == id)
        .findFirst()
        .orElse(null);
  }

  public static ChatMode getOrElse(final int id, final ChatMode mode) {
    return Arrays.stream(values())
        .filter(chatMode -> chatMode.getId() == id)
        .findFirst()
        .orElse(mode);
  }

}
