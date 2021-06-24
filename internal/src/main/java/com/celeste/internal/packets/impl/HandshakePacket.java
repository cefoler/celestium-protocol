package com.celeste.internal.packets.impl;

import com.celeste.internal.model.type.NextState;
import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.HandshakeMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;
import lombok.Getter;

/**
 * <p>First packet sent to the server
 *
 * <p>It contains the basic information of the client
 * such as version, address, port and NextState.
 * <p>The NextState can be LOGIN or STATUS</p>
 */
@Getter
public final class HandshakePacket extends AbstractPacket<HandshakeMessage> {

  public HandshakePacket() {
    super(0x00, null);
    System.out.println("RECEIVED HANDSHAKE PACKET");
  }

  @Override
  public Class<HandshakeMessage> getMessage() {
    return HandshakeMessage.class;
  }

  @Override
  public HandshakeMessage read(ProtocolBuffer buffer) {
    return HandshakeMessage.builder()
        .protocolVersion(buffer.readVarInt())
        .address(buffer.readString(255))
        .port(buffer.getByteBuf().readUnsignedShort())
        .state(NextState.getById(buffer.readVarInt()))
        .build();
  }

  @Override
  public void write(ProtocolBuffer buffer, HandshakeMessage packet) {
    // Handshake packet doesn't need to be written
  }

}

