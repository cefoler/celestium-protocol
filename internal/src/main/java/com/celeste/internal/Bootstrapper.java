package com.celeste.internal;

import com.celeste.internal.model.server.ServerAddress;
import com.celeste.internal.protocol.ServerBootstrapper;
import lombok.Getter;

@Getter
public final class Bootstrapper {

  private final ServerBootstrapper server;

  private Bootstrapper() {
    this.server = new ServerBootstrapper();
    start();
  }

  public static void main(final String[] args) {
    new Bootstrapper();
  }

  private void start() {
    server.initialize(new ServerAddress("localhost", 25565));
  }

}
