package com.celeste.internal.model.protocol.state;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

/**
 * Field received only on Handshake packet, should return STATUS or LOGIN.
 */
@Getter
@AllArgsConstructor
public enum NextState {

  STATUS(1),
  LOGIN(2);

  private final int id;

  @Nullable
  public static NextState get(final Integer id) {
    return Arrays.stream(values())
        .filter(nextState -> nextState.getId() == id)
        .findFirst()
        .orElse(null);
  }

}
