package com.celeste.internal.packets.messages.login;

import com.celeste.internal.annotation.Message;
import com.celeste.internal.packets.messages.PacketMessage;

@Message(id = 0x00)
public record LoginStartMessage(String username) implements PacketMessage {

}
