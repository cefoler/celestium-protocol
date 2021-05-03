package com.celeste.internal;

import com.celeste.internal.model.protocol.ServerAddress;
import com.celeste.internal.protocol.ServerBootstrapper;

public final class Bootstrapper {

  private ServerBootstrapper server;

  public Bootstrapper() {
    try {
      this.server = new ServerBootstrapper();
      start();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new Bootstrapper();
  }

  public void start() {
    server.init(ServerAddress.builder()
        .host("localhost")
        .port(25565)
        .build()
    );
  }

}
