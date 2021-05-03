package com.celeste.internal.registry;

import io.grpc.netty.shaded.io.netty.channel.SimpleChannelInboundHandler;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;

@Getter
public final class ChannelRegistry {

  public static final ChannelRegistry INSTANCE = new ChannelRegistry();

  private final Map<SocketAddress, Class<? extends SimpleChannelInboundHandler>> registry;

  public ChannelRegistry() {
    this.registry = new ConcurrentHashMap<>();
  }

  public void unregister(final Class<? extends SimpleChannelInboundHandler> clazz) {
    registry.remove(clazz);
  }

  public void unregister(final SocketAddress address) {
    registry.keySet().stream()
        .filter(address1 -> address1 == address)
        .forEach(registry::remove);
  }

}
