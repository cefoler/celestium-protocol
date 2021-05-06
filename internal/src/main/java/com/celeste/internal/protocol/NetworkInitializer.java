package com.celeste.internal.protocol;

import com.celeste.internal.controller.ChannelController;
import com.celeste.internal.protocol.codec.MessageConverter;
import com.celeste.internal.protocol.codec.MessageDecoder;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelInitializer;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.socket.SocketChannel;

public final class NetworkInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(final SocketChannel channel) {
    channel.config().setOption(ChannelOption.AUTO_CLOSE, true);
    channel.config().setOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000);

    final ChannelController controller = new ChannelController();

    channel.pipeline()
        .addFirst("converter", new MessageConverter(controller))
        .addAfter("converter", "decoder", new MessageDecoder(controller))
        .addAfter("decoder", "controller", controller);
  }

  @Override
  public void exceptionCaught(final ChannelHandlerContext context, final Throwable cause)  {
    cause.printStackTrace();
  }

}
