package com.celeste.internal.model.server;

import com.celeste.internal.model.protocol.ProtocolVersion;
import com.celeste.internal.packets.entity.PlayerSimpleEntity;
import com.celeste.minecraft.model.world.World;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public final class ServerSettings implements Serializable {

  private final String versionName;
  private final ProtocolVersion protocol;

  private final int maximumPlayers;
  private final int currentPlayers;
  private final List<PlayerSimpleEntity> onlinePlayers;

  private final String description;
  private final String icon;

  private final List<World> worlds;

  public ServerSettings() {
    this.versionName = "1.8.8";
    this.protocol = ProtocolVersion.EIGHT;
    this.maximumPlayers = 100;
    this.currentPlayers = 0;
    this.onlinePlayers = new ArrayList<>();
    this.description = "default";
    this.icon = "";
    this.worlds = new ArrayList<>();
    worlds.add(new World());
  }

  public World getDefaultWorld() {
    return worlds.stream()
        .filter(World::isDefault)
        .findFirst()
        .orElseThrow(() -> new IllegalStateException("No default worlds were created at the server."));
  }

}
