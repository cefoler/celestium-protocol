package com.celeste.internal.model.messages;

import com.celeste.internal.model.PacketContent;
import com.celeste.internal.model.protocol.type.NextState;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class HandshakeMessage implements PacketContent {

  private int protocolVersion;
  private String address;
  private int port;
  private NextState state;

}
