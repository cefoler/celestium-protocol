package com.celeste.internal.packets.messages.play;

import com.celeste.internal.annotation.Message;
import com.celeste.internal.packets.messages.PacketMessage;

@Message(id = 0x00)
public record KeepAliveMessage(long keepAliveId) implements PacketMessage {

}
