package com.celeste.internal.packets.impl.login;

import com.celeste.internal.annotation.Packet;
import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.login.LoginSuccessMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;

@Packet(outboundId = 0x02)
public final class LoginSuccessPacket extends AbstractPacket<LoginSuccessMessage> {

  @Override
  public void write(final ProtocolBuffer buffer, final LoginSuccessMessage packet) {
    buffer.writeUUID(packet.playerId());
    buffer.writeString(packet.username());
  }

}
