package com.celeste.internal.protocol;

import static com.celeste.internal.util.Logging.LOGGER;

import com.celeste.internal.model.ServerAddress;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.grpc.netty.shaded.io.netty.bootstrap.ServerBootstrap;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.epoll.EpollEventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerSocketChannel;
import java.time.Duration;
import java.time.Instant;
import lombok.Getter;
import lombok.SneakyThrows;

@Getter
public final class ServerBootstrapper {

  private ServerBootstrap server;
  private long startTime;

  @SneakyThrows
  public void init(final ServerAddress serverAddress) {
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
          .localAddress(serverAddress.getAddress(), serverAddress.getPort())
          .group(bossGroup, workerGroup)
          .childHandler(new NetworkInitializer());

      final ChannelFuture channelFuture = server.bind(serverAddress.getAddress(), serverAddress.getPort()).sync();

      LOGGER.atInfo().log("Server listening at %s:%s", serverAddress.getAddress(), serverAddress.getPort());
      LOGGER.atInfo().log("Duration of start: %s", Duration.between(Instant.ofEpochMilli(startTime), Instant.now()).toMillis());

      channelFuture.channel().closeFuture().sync();
    } catch (Exception exception) {
      LOGGER.atSevere().log("Server could not bind to the address, %s:%s", serverAddress.getAddress(), serverAddress.getPort());
    } finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }

}
