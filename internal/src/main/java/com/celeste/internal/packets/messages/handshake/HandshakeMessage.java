package com.celeste.internal.packets.messages.handshake;

import com.celeste.internal.annotation.Message;
import com.celeste.internal.model.protocol.state.NextState;
import com.celeste.internal.packets.messages.PacketMessage;

@Message(id = 0x00)
public record HandshakeMessage(int protocolVersion, String address, int port,
                               NextState state) implements PacketMessage {

}
