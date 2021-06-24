package com.celeste.internal.packets.entity;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public final class PlayerSimpleEntity {

  private final String name;
  private final UUID id;

}
