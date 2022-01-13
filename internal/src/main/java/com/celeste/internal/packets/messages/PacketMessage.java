package com.celeste.internal.packets.messages;

import com.celeste.internal.annotation.Message;
import com.celeste.internal.exception.protocol.impl.InvalidPacketException;

/**
 * The PacketContent is a interface
 * that helps in the packet organization.
 *
 * <p>It doesn't provide any informations besides the ID of the packet.</p>
 */
public interface PacketMessage {

  default int getId() {
    final Message message = getClass().getAnnotation(Message.class);
    if (message == null) {
      throw new InvalidPacketException("The message class '" + getClass().getSimpleName() + "' doesn't have a @Message on it.");
    }

    return message.id();
  }

}
