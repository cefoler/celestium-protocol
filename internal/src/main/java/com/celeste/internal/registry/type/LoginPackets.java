package com.celeste.internal.registry.type;

import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.packets.impl.handshake.LoginResponsePacket;
import com.celeste.internal.packets.impl.login.LoginStartPacket;
import com.celeste.internal.packets.impl.login.LoginSuccessPacket;
import com.celeste.internal.packets.messages.login.LoginStartMessage;
import com.celeste.internal.packets.messages.login.LoginSuccessMessage;
import com.celeste.internal.packets.messages.status.StatusResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum LoginPackets {

  START(0x00, null, LoginStartMessage.class, new LoginStartPacket()),
  SUCCESS(null, 0x02, LoginSuccessMessage.class, new LoginSuccessPacket()),
  RESPONSE(0x00, null, StatusResponseMessage.class, new LoginResponsePacket());

  private final Integer inboundId;
  private final Integer outboundId;
  private final Class<? extends PacketContent> message;
  private final AbstractPacket<? extends PacketContent> packet;

  public static LoginPackets getByMessage(Class<? extends PacketContent> message) {
    return Arrays.stream(values())
        .filter(loginPackets -> loginPackets.getMessage() == message)
        .findFirst()
        .orElseThrow(null);
  }

  public static LoginPackets getInbound(int id) {
    return Arrays.stream(values())
        .filter(loginPackets -> loginPackets.getInboundId() == id)
        .findFirst()
        .orElseThrow(null);
  }

  public static LoginPackets getOutbound(int id) {
    return Arrays.stream(values())
        .filter(loginPackets -> loginPackets.getOutboundId() == id)
        .findFirst()
        .orElseThrow(null);
  }

}
