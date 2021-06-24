package com.celeste.internal.registry;

import com.celeste.internal.packets.handlers.KeepAliveHandler;
import com.celeste.library.core.model.registry.Registry;
import com.celeste.library.core.model.registry.impl.LinkedRegistry;
import com.celeste.library.core.util.Logger;
import lombok.Getter;

import java.util.UUID;

@Getter
public final class KeepAliveRegistry {

  public static final KeepAliveRegistry INSTANCE;

  static {
    INSTANCE = new KeepAliveRegistry();
  }

  private final Registry<UUID, KeepAliveHandler> registry;

  public KeepAliveRegistry() {
    this.registry = new LinkedRegistry<>();
  }

  public void register(final UUID id, final KeepAliveHandler handler) {
    registry.register(id, handler);
    Logger.getLogger().atInfo().log("A new keep alive has been registered! ID: %s", id);
  }

  public void unregister(final UUID id) {
    registry.remove(id);
    Logger.getLogger().atInfo().log("A new keep alive has been unregistered! ID: %s", id);
  }

}
