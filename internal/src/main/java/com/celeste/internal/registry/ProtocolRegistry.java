package com.celeste.internal.registry;

public interface ProtocolRegistry<K, T> {

  T get(final K id);

  void register(final K id, final T type);

  void unregister(final K id);

}
