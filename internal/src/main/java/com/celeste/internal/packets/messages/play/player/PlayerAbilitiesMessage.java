package com.celeste.internal.packets.messages.play.player;

import com.celeste.internal.annotation.Message;
import com.celeste.internal.model.client.type.Flags;
import com.celeste.internal.packets.PacketContent;

@Message(id = 0x32)
public record PlayerAbilitiesMessage(Flags flags, float flyingSpeed, float viewModifier) implements PacketContent {

  public PlayerAbilitiesMessage() {
    this(Flags.ALLOW_FLYING, 0.05F, 0.1F);
  }

}
