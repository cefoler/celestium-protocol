package com.celeste.internal.packets.messages.login;

import com.celeste.internal.packets.PacketContent;
import lombok.Data;

@Data
public class LoginResponseMessage implements PacketContent {

  private final byte payload;

}
