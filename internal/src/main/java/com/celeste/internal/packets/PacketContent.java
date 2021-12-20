package com.celeste.internal.packets;

import com.celeste.internal.annotation.Message;

/**
 * The PacketContent is a interface
 * that helps in the packet organization.
 *
 * <p>It doesn't provide any informations besides the ID of the packet.</p>
 */
public interface PacketContent {

  default int getId() {
    final Message message = getClass().getAnnotation(Message.class);
    if (message == null) {
      return 999999;
    }

    return message.id();
  }

}
