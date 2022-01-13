package com.celeste.internal.packets.messages.login;

import com.celeste.internal.annotation.Message;
import com.celeste.internal.packets.messages.PacketMessage;

import java.util.UUID;

@Message(id = 0x02)
public record LoginSuccessMessage(UUID playerId, String username) implements PacketMessage {

}
