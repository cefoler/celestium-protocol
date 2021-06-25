package com.celeste.internal.registry;

import com.celeste.internal.model.protocol.ConnectionState;
import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.packets.impl.HandshakePacket;

import com.celeste.internal.registry.type.LoginPackets;
import com.celeste.internal.registry.type.PlayPackets;

public final class Protocol {

  public static final Protocol INSTANCE;

  static {
    INSTANCE = new Protocol();
  }

  public AbstractPacket<? extends PacketContent> getPacketInbound(final ConnectionState stage, final int packetId) {
    return switch (stage) {
      case HANDSHAKE -> new HandshakePacket();
      case LOGIN -> LoginPackets.getInbound(packetId).getPacket();
      case PLAY -> PlayPackets.getInbound(packetId).getPacket();
      default -> null;
    };
  }

  public AbstractPacket<? extends PacketContent> getPacketOutbound(final ConnectionState state, final Class<? extends PacketContent> packet) {
    return switch (state) {
      case LOGIN -> LoginPackets.getByMessage(packet).getPacket();
      case PLAY -> PlayPackets.getByMessage(packet).getPacket();
      default -> null;
    };
  }

}
