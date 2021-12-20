package com.celeste.internal.packets.messages;

import com.celeste.internal.annotation.Message;
import com.celeste.internal.model.protocol.state.NextState;
import com.celeste.internal.packets.PacketContent;

@Message(id = 0x00)
public record HandshakeMessage(int protocolVersion, String address, int port, NextState state) implements PacketContent {

}
