package com.celeste.internal.packets.messages;

import com.celeste.internal.packets.PacketContent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class LoginStartMessage implements PacketContent {

  private String username;

}
