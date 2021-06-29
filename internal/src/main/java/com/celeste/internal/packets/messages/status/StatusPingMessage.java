package com.celeste.internal.packets.messages.status;

import com.celeste.internal.packets.PacketContent;
import lombok.Data;

@Data
public final class StatusPingMessage implements PacketContent {

  private final long number;

  @Override
  public int getId() {
    return 0x01;
  }

}
