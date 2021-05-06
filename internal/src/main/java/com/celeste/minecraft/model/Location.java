package com.celeste.minecraft.model;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class Location implements Serializable {

  private float x;
  private float y;
  private float z;

  private float yaw;
  private float pitch;

}
