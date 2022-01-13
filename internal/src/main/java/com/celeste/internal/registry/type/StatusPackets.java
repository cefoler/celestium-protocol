package com.celeste.internal.registry.type;

import com.celeste.internal.packets.Packet;
import com.celeste.internal.packets.messages.PacketMessage;
import com.celeste.internal.packets.impl.handshake.StatusPingPacket;
import com.celeste.internal.packets.impl.handshake.StatusResponsePacket;
import com.celeste.internal.packets.messages.status.StatusPingMessage;
import com.celeste.internal.packets.messages.status.StatusResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum StatusPackets {

  RESPONSE(0x00, 0x00, StatusResponseMessage.class, new StatusResponsePacket()),
  PING(0x01, 0x01, StatusPingMessage.class, new StatusPingPacket());

  private final Integer inboundId;
  private final Integer outboundId;
  private final Class<? extends PacketMessage> message;
  private final Packet<? extends PacketMessage> packet;

  public static StatusPackets getByMessage(Class<? extends PacketMessage> message) {
    return Arrays.stream(values())
        .filter(packets -> packets.getMessage() == message)
        .findFirst()
        .orElse(null);
  }

  public static StatusPackets getInbound(int id) {
    return Arrays.stream(values())
        .filter(packets -> packets.getInboundId() == id)
        .findFirst()
        .orElse(null);
  }

  public static StatusPackets getOutbound(int id) {
    return Arrays.stream(values())
        .filter(packets -> packets.getOutboundId() == id)
        .findFirst()
        .orElse(null);
  }

}
