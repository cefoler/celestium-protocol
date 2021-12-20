package com.celeste.internal.protocol;

import com.celeste.internal.model.server.ServerAddress;
import com.celeste.library.core.factory.ThreadingFactory;
import com.celeste.library.core.util.Logger;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.grpc.netty.shaded.io.netty.bootstrap.ServerBootstrap;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.epoll.EpollEventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerSocketChannel;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

import io.grpc.netty.shaded.io.netty.channel.nio.NioEventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.SystemUtils;

@Getter
public final class ServerBootstrapper {

  public static final ScheduledExecutorService SCHEDULED;

  static {
    SCHEDULED = ThreadingFactory.scheduledThreadPool(8);
  }

  private ServerBootstrap server;
  private long startTime;

  private boolean started;

  @SneakyThrows
  public CompletableFuture<Void> initialize(final ServerAddress serverAddress) {
    return CompletableFuture.runAsync(() -> {
      this.startTime = System.currentTimeMillis();
      this.started = false;

      final boolean linux = SystemUtils.IS_OS_LINUX;

      final ThreadFactory bossFactory = new ThreadFactoryBuilder()
          .setNameFormat("celestium-server-boss - %d")
          .setDaemon(true)
          .setPriority(100)
          .build();

      final EventLoopGroup bossGroup = linux
          ? new EpollEventLoopGroup(0, bossFactory)
          : new NioEventLoopGroup(0, bossFactory);

      final ThreadFactory workerFactory = new ThreadFactoryBuilder()
          .setNameFormat("celestium-server-worker - %d")
          .setDaemon(true)
          .setPriority(90)
          .build();

      final EventLoopGroup workerGroup = linux
          ? new EpollEventLoopGroup(0, workerFactory)
          : new NioEventLoopGroup(0, workerFactory);

      try {
        final String address = serverAddress.address();
        final int port = serverAddress.port();

        this.server = new ServerBootstrap()
            .channel(linux ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
            .localAddress(address, port)
            .group(bossGroup, workerGroup)
            .childHandler(new NetworkInitializer());

        final ChannelFuture channelFuture = server.bind(address, port)
            .sync();

        Logger.getLogger().atInfo().log("Server listening at %s:%s", address, port);
        Logger.getLogger().atInfo().log("Duration of start: %sms", Duration.between(Instant.ofEpochMilli(startTime), Instant.now()).toMillis());

        channelFuture.channel()
            .closeFuture()
            .sync();

        this.started = true;
      } catch (final Exception exception) {
        Logger.getLogger().atSevere().log("Server could not bind to the address, %s:%s", serverAddress.address(), serverAddress.port());
      } finally {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();

        this.started = false;
      }
    });
  }

}
