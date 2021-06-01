package com.celeste.internal.registry;

import com.celeste.internal.packets.handlers.KeepAliveHandler;
import com.celeste.internal.util.Logging;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public final class KeepAliveRegistry {

  public static final KeepAliveRegistry INSTANCE = new KeepAliveRegistry();

  private final Map<UUID, KeepAliveHandler> registry;

  public KeepAliveRegistry() {
    this.registry = new LinkedHashMap<>();
  }

  public void register(final UUID id, final KeepAliveHandler handler) {
    registry.put(id, handler);
    Logging.LOGGER.atInfo().log("A new keep alive has been registered! ID: %s", id);
  }

  public void unregister(final UUID id) {
    registry.remove(id);
  }

}
