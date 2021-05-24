package com.celeste.internal.registry;

import com.celeste.internal.model.type.ConnectionState;
import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.packets.impl.login.LoginStartPacket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;

@Getter
public final class ProtocolRegistry {

  public static final ProtocolRegistry INSTANCE = new ProtocolRegistry();

  private final Map<Class<? extends PacketContent>, AbstractPacket<?>> statusPackets;
  private final Map<Class<? extends PacketContent>, AbstractPacket<?>> loginPackets;
  private final Map<Class<? extends PacketContent>, AbstractPacket<?>> playPackets;

  public ProtocolRegistry() {
    this.statusPackets = new ConcurrentHashMap<>();
    this.loginPackets = new ConcurrentHashMap<>();
    this.playPackets = new ConcurrentHashMap<>();

    register(new LoginStartPacket(), ConnectionState.LOGIN);
  }

  private void register(final AbstractPacket<? extends PacketContent> packet, final ConnectionState state) {
    switch (state) {
      case STATUS: statusPackets.put(packet.getMessage(), packet);
      case LOGIN: loginPackets.put(packet.getMessage(), packet);
      case PLAY: playPackets.put(packet.getMessage(), packet);
    }
  }

}
