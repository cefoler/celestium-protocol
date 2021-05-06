package com.celeste.internal.packets.impl;

import com.celeste.internal.packets.Packet;
import com.celeste.internal.packets.messages.LoginStartMessage;
import com.celeste.internal.protocol.util.ProtocolBuffer;

public final class LoginStartPacket extends Packet<LoginStartMessage> {

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

    // TODO:
  }

  @Override
  public void write(final ProtocolBuffer buffer, final LoginStartMessage packet) {
    // LoginStartPacket doesn't need to be written
  }

}
