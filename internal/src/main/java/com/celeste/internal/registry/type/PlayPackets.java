package com.celeste.internal.registry.type;

import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.packets.impl.play.JoinPacket;
import com.celeste.internal.packets.impl.play.KeepAlivePacket;
import com.celeste.internal.packets.messages.play.JoinMessage;
import com.celeste.internal.packets.messages.play.KeepAliveMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PlayPackets {

  KEEP_ALIVE(null, 0x10, KeepAliveMessage.class, new KeepAlivePacket()),
  JOIN(null, 0x26, JoinMessage.class, new JoinPacket());

  private final Integer inboundId;
  private final Integer outboundId;
  private final Class<? extends PacketContent> message;
  private final AbstractPacket<? extends PacketContent> packet;

  public static PlayPackets getByMessage(Class<? extends PacketContent> message) {
    return Arrays.stream(values())
        .filter(packets -> packets.getMessage() == message)
        .findFirst()
        .orElseThrow(null);
  }

  public static PlayPackets getInbound(int id) {
    return Arrays.stream(values())
        .filter(packets -> packets.getInboundId() == id)
        .findFirst()
        .orElseThrow(null);
  }

  public static PlayPackets getOutbound(int id) {
    return Arrays.stream(values())
        .filter(packets -> packets.getOutboundId() == id)
        .findFirst()
        .orElseThrow(null);
  }

}