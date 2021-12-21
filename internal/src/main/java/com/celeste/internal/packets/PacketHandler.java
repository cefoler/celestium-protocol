package com.celeste.internal.packets;

import com.celeste.internal.controller.ChannelController;
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
public abstract class PacketHandler {

  private final ChannelController controller;

  /**
   * The handler is able to read the packet through the Context
   * or the formatted {@link PacketContent}, that should cast
   * as the actual message object of that handler.
   *
   * @param context ChannelHandlerContext
   * @param message PacketContent
   */
  public abstract void read(final ChannelHandlerContext context, final PacketContent message);

  /**
   * Dispatchs multiple {@link PacketContent} into the {@link ChannelController}
   * @param packets PacketContent
   */
  public void dispatch(final PacketContent... packets) {
    for (PacketContent packet : packets) {
      controller.dispatch(packet);
    }
  }

}
