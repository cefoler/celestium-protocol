package com.celeste.internal.packets.handler;

import com.celeste.internal.controller.ChannelController;
import com.celeste.internal.packets.messages.PacketMessage;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The PacketHandler should be extended in all
 * handler of packets. It contain main methods
 * for handling the receive and dispatch of messages.
 */
@Getter
@AllArgsConstructor
public abstract class AbstractPacketHandler implements PacketHandler {

  private final ChannelController controller;

  /**
   * The handler is able to read the packet through the Context
   * or the formatted {@link PacketMessage}, that should cast
   * as the actual message object of that handler.
   *
   * @param context ChannelHandlerContext
   * @param message PacketContent
   */
  public abstract void read(final ChannelHandlerContext context, final PacketMessage message);

  /**
   * Dispatchs multiple {@link PacketMessage} into the {@link ChannelController}
   *
   * @param packets PacketContent
   */
  public void dispatch(final PacketMessage... packets) {
    for (PacketMessage packet : packets) {
      controller.dispatch(packet);
    }
  }

}
