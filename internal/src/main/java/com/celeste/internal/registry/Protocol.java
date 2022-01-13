package com.celeste.internal.registry;

import com.celeste.internal.model.protocol.ConnectionState;
import com.celeste.internal.packets.Packet;
import com.celeste.internal.packets.messages.PacketMessage;
import com.celeste.internal.packets.impl.handshake.HandshakePacket;
import com.celeste.internal.registry.type.LoginPackets;
import com.celeste.internal.registry.type.PlayPackets;
import com.celeste.internal.registry.type.StatusPackets;

public final class Protocol {

  public static Packet<? extends PacketMessage> getPacketInbound(final ConnectionState stage, final int packetId) {
    return switch (stage) {
      case HANDSHAKE -> new HandshakePacket();
      case STATUS -> StatusPackets.getInbound(packetId).getPacket();
      case LOGIN -> LoginPackets.getInbound(packetId).getPacket();
      case PLAY -> PlayPackets.getInbound(packetId).getPacket();
    };
  }

  public static Packet<? extends PacketMessage> getPacketOutbound(final ConnectionState stage, final int packetId) {
    return switch (stage) {
      case HANDSHAKE -> new HandshakePacket();
      case STATUS -> StatusPackets.getOutbound(packetId).getPacket();
      case LOGIN -> LoginPackets.getOutbound(packetId).getPacket();
      case PLAY -> PlayPackets.getOutbound(packetId).getPacket();
    };
  }

  public static Packet<? extends PacketMessage> getPacketOutbound(final ConnectionState state, final Class<? extends PacketMessage> packet) {
    return switch (state) {
      case STATUS -> StatusPackets.getByMessage(packet).getPacket();
      case LOGIN -> LoginPackets.getByMessage(packet).getPacket();
      case PLAY -> PlayPackets.getByMessage(packet).getPacket();
      default -> null;
    };
  }

}
