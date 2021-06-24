package com.celeste.minecraft.model;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public final class Location implements Serializable {

  private float x, y, z;

  private float yaw;
  private float pitch;

}
