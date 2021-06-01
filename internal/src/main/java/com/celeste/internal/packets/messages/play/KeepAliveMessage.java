package com.celeste.internal.packets.messages.play;

import com.celeste.internal.packets.PacketContent;
import lombok.Data;

@Data
public final class KeepAliveMessage implements PacketContent {

  private final long id;

}
