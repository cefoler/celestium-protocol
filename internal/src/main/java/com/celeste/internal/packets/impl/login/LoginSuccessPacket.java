package com.celeste.internal.packets.impl.login;

import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.login.LoginSuccessMessage;
import com.celeste.internal.protocol.util.ProtocolBuffer;

public class LoginSuccessPacket extends AbstractPacket<LoginSuccessMessage> {

  public LoginSuccessPacket() {
    super(null, 0x02);
  }

  @Override
  public Class<LoginSuccessMessage> getMessage() {
    return LoginSuccessMessage.class;
  }

  @Override
  public LoginSuccessMessage read(final ProtocolBuffer buffer) {
    // The LoginSuccess is only sent not received, meaning the read method isn't needed.
    return null;
  }

  @Override
  public void write(final ProtocolBuffer buffer, final LoginSuccessMessage packet) {
    buffer.writeUUID(packet.getId());
    buffer.writeString(packet.getUsername());
  }

}
