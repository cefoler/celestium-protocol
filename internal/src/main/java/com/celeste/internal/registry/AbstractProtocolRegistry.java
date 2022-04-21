package com.celeste.internal.registry;

import com.celeste.library.core.model.registry.Registry;
import com.celeste.library.core.model.registry.impl.LinkedRegistry;
import com.celeste.library.core.util.Logger;


public abstract class AbstractProtocolRegistry<K, T> implements ProtocolRegistry<K, T> {

  private final Registry<K, T> registry;

  public AbstractProtocolRegistry() {
    this.registry = new LinkedRegistry<>();
  }

  @Override
  public T get(final K id) {
    return registry.get(id);
  }

  public void register(final K id, final T type) {
    registry.register(id, type);
    Logger.getLogger().atInfo().log("A value has been registered! ID: %s", id);
  }

  public void unregister(final K id) {
    registry.remove(id);
    Logger.getLogger().atInfo().log("A value has been unregistered! ID: %s", id);
  }

}
