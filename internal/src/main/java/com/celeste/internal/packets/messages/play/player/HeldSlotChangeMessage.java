package com.celeste.internal.packets.messages.play.player;

import com.celeste.internal.annotation.Message;
import com.celeste.internal.packets.PacketContent;


@Message(id = 0x48)
public record HeldSlotChangeMessage(int slot) implements PacketContent {

}
