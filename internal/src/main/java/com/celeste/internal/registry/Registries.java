package com.celeste.internal.registry;

import com.celeste.internal.model.player.impl.PlayerConnection;
import com.celeste.internal.packets.handler.PacketHandler;
import com.celeste.internal.registry.impl.DefaultProtocolRegistry;

import java.util.UUID;

public final class Registries {

  public static final ProtocolRegistry<UUID, PlayerConnection> CONNECTION;
  public static final ProtocolRegistry<UUID, PacketHandler> KEEP_ALIVE;

  static {
    CONNECTION = new DefaultProtocolRegistry<>();
    KEEP_ALIVE = new DefaultProtocolRegistry<>();
  }

}
