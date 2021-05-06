package com.celeste.internal;

import com.celeste.internal.exception.BootstrapperException;
import com.celeste.internal.model.ServerAddress;
import com.celeste.internal.protocol.ServerBootstrapper;

public final class Bootstrapper {

  private final ServerBootstrapper server;

  public Bootstrapper() {
    this.server = new ServerBootstrapper();
    if (server.isStarted()) {
      throw new BootstrapperException("The server is already connected!");
    }

    start();
  }

  public static void main(String[] args) {
    new Bootstrapper();
  }

  public void start() {
    server.init(ServerAddress.builder()
        .address("localhost")
        .port(25565)
        .build()
    );
  }

}
