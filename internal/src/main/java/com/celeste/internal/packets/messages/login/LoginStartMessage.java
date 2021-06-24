package com.celeste.internal.packets.messages.login;

import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.registry.type.LoginPackets;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class LoginStartMessage implements PacketContent {

  private String username;

  @Override
  public int getId() {
    return LoginPackets.START.getInboundId();
  }

}
