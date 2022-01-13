package com.celeste.internal.registry.type;

import com.celeste.internal.packets.Packet;
import com.celeste.internal.packets.messages.PacketMessage;
import com.celeste.internal.packets.impl.login.LoginStartPacket;
import com.celeste.internal.packets.impl.login.LoginSuccessPacket;
import com.celeste.internal.packets.messages.login.LoginStartMessage;
import com.celeste.internal.packets.messages.login.LoginSuccessMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum LoginPackets {

  START(0x00, null, LoginStartMessage.class, new LoginStartPacket()),
  SUCCESS(null, 0x02, LoginSuccessMessage.class, new LoginSuccessPacket());

  private final Integer inboundId;
  private final Integer outboundId;
  private final Class<? extends PacketMessage> message;
  private final Packet<? extends PacketMessage> packet;

  public static LoginPackets getByMessage(Class<? extends PacketMessage> message) {
    return Arrays.stream(values())
        .filter(loginPackets -> loginPackets.getMessage() == message)
        .findFirst()
        .orElse(null);
  }

  public static LoginPackets getInbound(int id) {
    return Arrays.stream(values())
        .filter(loginPackets -> loginPackets.getInboundId() == id)
        .findFirst()
        .orElse(null);
  }

  public static LoginPackets getOutbound(int id) {
    return Arrays.stream(values())
        .filter(loginPackets -> loginPackets.getOutboundId() == id)
        .findFirst()
        .orElse(null);
  }

}
