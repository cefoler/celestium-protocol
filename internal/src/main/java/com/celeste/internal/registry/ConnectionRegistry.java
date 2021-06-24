package com.celeste.internal.registry;

import com.celeste.internal.model.impl.PlayerConnection;
import java.util.UUID;

import com.celeste.library.core.model.registry.Registry;
import com.celeste.library.core.model.registry.impl.LinkedRegistry;
import com.celeste.library.core.util.Logger;
import lombok.Getter;

@Getter
public final class ConnectionRegistry {

  public static final ConnectionRegistry INSTANCE;

  static {
    INSTANCE = new ConnectionRegistry();
  }

  private final Registry<UUID, PlayerConnection> registry;

  public ConnectionRegistry() {
    this.registry = new LinkedRegistry<>();
  }

  public void register(final UUID id, final PlayerConnection connection) {
    registry.register(id, connection);
    Logger.getLogger().atInfo().log("A new player has been registered! ID: %s, Name: %s", connection.getId(), connection.getName());
  }

  public void unregister(final UUID id) {
    registry.remove(id);
    Logger.getLogger().atInfo().log("A new player has been unregistered! ID: %s", id);
  }

}
