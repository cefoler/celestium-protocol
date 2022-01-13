package com.celeste.internal.model.player.impl;

import com.celeste.game.model.Location;
import com.celeste.game.model.type.Gamemode;
import com.celeste.internal.model.player.Connection;
import com.celeste.internal.model.protocol.ConnectionState;
import com.celeste.internal.model.protocol.ProtocolVersion;
import com.mojang.authlib.GameProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.net.InetSocketAddress;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public final class PlayerConnection implements Connection {

  private final GameProfile gameProfile;
  private final String name;
  private final InetSocketAddress address;
  private final ProtocolVersion protocolVersion;
  private final long firstJoin;
  private String displayName;
  private ConnectionState state;
  private int latency;
  private Location location;
  private Gamemode gamemode;

  @Override
  public UUID getId() {
    return gameProfile.getId();
  }

}
