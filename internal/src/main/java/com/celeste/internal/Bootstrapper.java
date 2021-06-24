package com.celeste.internal;

import com.celeste.internal.model.ServerAddress;
import com.celeste.internal.protocol.ServerBootstrapper;
import lombok.Getter;

@Getter
public final class Bootstrapper {

  private final ServerBootstrapper server;

  public Bootstrapper() {
    this.server = new ServerBootstrapper();
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
