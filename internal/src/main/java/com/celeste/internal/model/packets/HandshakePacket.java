package com.celeste.internal.model.packets;

import com.celeste.internal.model.Packet;
import com.celeste.internal.model.messages.HandshakeMessage;
import com.celeste.internal.model.protocol.type.NextState;
import com.celeste.internal.protocol.util.ProtocolBuffer;
import lombok.Getter;

/**
 * <p>First packet sent to the server
 *
 * <p>It contains the basic information of the client
 * such as version, address, port and NextState.
 * <p>The NextState can be LOGIN or STATUS</p>
 */
@Getter
public final class HandshakePacket extends Packet<HandshakeMessage> {

  public HandshakePacket() {
    super(0);
    System.out.println("RECEIVED HANDSHAKE PACKET");
  }

  @Override
  public Class<HandshakeMessage> getType() {
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

