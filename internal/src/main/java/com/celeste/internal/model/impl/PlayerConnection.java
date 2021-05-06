package com.celeste.internal.model.impl;

import com.celeste.internal.model.Connection;
import com.celeste.internal.model.type.ConnectionState;
import com.celeste.minecraft.model.Location;
import com.celeste.minecraft.model.type.Gamemode;
import com.mojang.authlib.GameProfile;
import java.net.InetSocketAddress;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerConnection implements Connection {

  private final GameProfile gameProfile;
  private final String name;

  private final ConnectionState state;
  private final int latency;
  private final InetSocketAddress address;

  private final int protocolVersion;
  private final long firstJoin;

  private final Location location;
  private final Gamemode gamemode;

  @Override
  public UUID getId() {
    return gameProfile.getId();
  }

}
