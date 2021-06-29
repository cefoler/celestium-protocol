package com.celeste.internal.packets.messages.login;

import com.celeste.internal.packets.PacketContent;
import java.util.UUID;

import com.celeste.internal.registry.type.LoginPackets;
import lombok.Data;

@Data
public final class LoginSuccessMessage implements PacketContent {

  private final UUID playerId;
  private final String username;

  @Override
  public int getId() {
    return LoginPackets.SUCCESS.getOutboundId();
  }

}
