package com.celeste.internal.packets.impl.login;

import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.login.LoginStartMessage;
import com.celeste.internal.protocol.util.ProtocolBuffer;

public final class LoginStartPacket extends AbstractPacket<LoginStartMessage> {

  public LoginStartPacket() {
    super(0x00, null);
    System.out.println("RECEIVED LOGIN PACKET");
  }

  @Override
  public Class<LoginStartMessage> getMessage() {
    return LoginStartMessage.class;
  }

  @Override
  public LoginStartMessage read(final ProtocolBuffer buffer) {
    return new LoginStartMessage(buffer.readString(16));
  }

  @Override
  public void write(final ProtocolBuffer buffer, final LoginStartMessage packet) {
    // LoginStartPacket doesn't need to be written
  }

}
