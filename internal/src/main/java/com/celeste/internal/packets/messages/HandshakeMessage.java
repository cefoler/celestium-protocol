package com.celeste.internal.packets.messages;

import com.celeste.internal.model.type.NextState;
import com.celeste.internal.packets.PacketContent;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class HandshakeMessage implements PacketContent {

  private int protocolVersion;
  private String address;
  private int port;
  private NextState state;

  @Override
  public int getId() {
    return 0x00;
  }

}
