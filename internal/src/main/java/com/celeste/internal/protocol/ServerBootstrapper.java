package com.celeste.internal.protocol;

import com.celeste.internal.model.protocol.ServerAddress;
import com.google.common.flogger.FluentLogger;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.grpc.netty.shaded.io.netty.bootstrap.ServerBootstrap;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.epoll.EpollEventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerSocketChannel;
import lombok.Getter;
import lombok.SneakyThrows;

@Getter
public final class ServerBootstrapper {

  private static final FluentLogger LOGGER = FluentLogger.forEnclosingClass();

  private ServerBootstrap server;
  private long startTime;

  @SneakyThrows
  public void init(final ServerAddress address) {
    this.startTime = System.currentTimeMillis();

    final EventLoopGroup bossGroup = new EpollEventLoopGroup(0, new ThreadFactoryBuilder()
        .setNameFormat("celestium-server-boss - %d")
        .setDaemon(true)
        .setPriority(6)
        .build()
    );

    final EventLoopGroup workerGroup = new EpollEventLoopGroup(0, new ThreadFactoryBuilder()
        .setNameFormat("celestium-server-worker - %d")
        .setDaemon(true)
        .setPriority(5)
        .build()
    );

    try {
      this.server = new ServerBootstrap()
          .channel(EpollServerSocketChannel.class)
          .localAddress(address.getHost(), address.getPort())
          .group(bossGroup, workerGroup)
          .option(ChannelOption.TCP_NODELAY, true)
          .option(ChannelOption.SO_KEEPALIVE, true)
          .childHandler(new NetworkInitializer());

      final ChannelFuture channelFuture = server.bind(address.getHost(), address.getPort()).sync();
      LOGGER.atInfo().log("Server listening at %s:%s", address.getHost(), address.getPort());

      channelFuture.channel().closeFuture().sync();
    } finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }

}
