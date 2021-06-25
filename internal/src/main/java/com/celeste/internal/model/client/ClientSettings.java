package com.celeste.internal.model.client;

import com.celeste.internal.model.client.type.ChatMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Locale;

@Data
@Builder
@AllArgsConstructor
public final class ClientSettings {

  private final Locale local;
  private final int viewDistance;

  private final ChatMode mode;
  private final boolean chatColors;

  // 0: left, 1: right
  private final int mainHand;

}
