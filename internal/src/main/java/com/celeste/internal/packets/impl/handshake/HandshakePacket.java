package com.celeste.internal.packets.impl.handshake;

import com.celeste.internal.annotation.Packet;
import com.celeste.internal.model.protocol.state.NextState;
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
@Packet(inboundId = 0x00)
public final class HandshakePacket extends AbstractPacket<HandshakeMessage> {

  @Override
  public HandshakeMessage read(final ProtocolBuffer buffer) {
    return new HandshakeMessage(
        buffer.readVarInt(),
        buffer.readString(255),
        buffer.getByteBuf().readUnsignedShort(),
        NextState.get(buffer.readVarInt())
    );
  }

}

