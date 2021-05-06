package com.celeste.internal.packets.messages;

import com.celeste.internal.packets.PacketContent;
import java.util.UUID;
import lombok.Data;

@Data
public final class LoginSuccessMessage implements PacketContent {

  private final UUID id;
  private final String username;

}
