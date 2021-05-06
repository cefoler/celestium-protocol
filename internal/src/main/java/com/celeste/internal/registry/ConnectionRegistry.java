package com.celeste.internal.registry;

import com.celeste.internal.model.impl.PlayerConnection;
import com.celeste.internal.util.Logging;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;

@Getter
public final class ConnectionRegistry {

  public static final ConnectionRegistry INSTANCE = new ConnectionRegistry();

  private final Map<UUID, PlayerConnection> registry;

  public ConnectionRegistry() {
    this.registry = new LinkedHashMap<>();
  }

  public void register(final UUID id, final PlayerConnection connection) {
    registry.put(id, connection);
    Logging.LOGGER.atInfo().log("A new player has been registered! ID: %s, Name: %s", connection.getId(), connection.getName());
  }

  public void unregister(final UUID id) {
    registry.remove(id);
  }

}
