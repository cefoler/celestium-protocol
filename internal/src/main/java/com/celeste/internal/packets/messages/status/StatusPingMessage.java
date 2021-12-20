package com.celeste.internal.packets.messages.status;

import com.celeste.internal.annotation.Message;
import com.celeste.internal.packets.PacketContent;

@Message(id = 0x01)
public record StatusPingMessage(long number) implements PacketContent {

}
