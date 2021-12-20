package com.celeste.internal.packets.impl.login;

import com.celeste.internal.annotation.Packet;
import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.login.LoginStartMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;

@Packet(inboundId = 0x00)
public final class LoginStartPacket extends AbstractPacket<LoginStartMessage> {

  @Override
  public LoginStartMessage read(final ProtocolBuffer buffer) {
    return new LoginStartMessage(buffer.readString(16));
  }

}
