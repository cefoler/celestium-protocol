package com.celeste.internal.protocol;

import com.celeste.internal.controllers.ChannelController;
import com.celeste.internal.protocol.codec.MessageReader;
import com.celeste.internal.protocol.codec.MessageDecoder;
import com.celeste.internal.protocol.codec.MessageEncoder;
import com.celeste.internal.protocol.codec.MessageWriter;
import com.celeste.library.core.util.Logger;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelInitializer;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.socket.SocketChannel;

public final class NetworkInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(final SocketChannel channel) {
    channel.config().setOption(ChannelOption.AUTO_CLOSE, true);
    channel.config().setOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000);

    final ChannelController controller = new ChannelController(channel);

    channel.pipeline()
        .addFirst("converter", new MessageReader(controller))
        .addAfter("converter", "decoder", new MessageDecoder(controller))
        .addAfter("decoder", "controller", controller)
        .addAfter("controller", "encoder", new MessageEncoder(controller))
        .addAfter("encoder", "writer", new MessageWriter());
  }

  @Override
  public void exceptionCaught(final ChannelHandlerContext context, final Throwable cause)  {
    Logger.getLogger().atWarning().log(cause.getMessage());
  }

}
