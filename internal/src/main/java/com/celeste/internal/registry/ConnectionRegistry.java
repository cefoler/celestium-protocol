package com.celeste.internal.registry;

import com.celeste.internal.model.impl.PlayerConnection;
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

}
