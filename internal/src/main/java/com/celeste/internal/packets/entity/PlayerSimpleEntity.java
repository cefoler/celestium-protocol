package com.celeste.internal.packets.entity;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public final class PlayerSimpleEntity {

  private final String name;
  private final UUID id;

}
