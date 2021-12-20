package com.celeste.internal.model.server;

import com.celeste.internal.model.protocol.ProtocolVersion;
import com.celeste.internal.packets.entity.PlayerSimpleEntity;
import com.celeste.game.model.world.World;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public record ServerSettings(String versionName, ProtocolVersion protocol, int maximumPlayers,
                             int currentPlayers, List<PlayerSimpleEntity> onlinePlayers, String description,
                             String icon, List<World> worlds) implements Serializable {

  public ServerSettings() {
    this("1.8.9", ProtocolVersion.EIGHT, 100,
        0, new ArrayList<>(), "default", "",
        new ArrayList<>()
    );

    worlds.add(new World());
  }

  public World getDefaultWorld() {
    return worlds.stream()
        .filter(World::defaultWorld)
        .findFirst()
        .orElseThrow(() -> new IllegalStateException("No default worlds were created at the server."));
  }

}
