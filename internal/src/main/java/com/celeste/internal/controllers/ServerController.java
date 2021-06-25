package com.celeste.internal.controllers;

import com.celeste.internal.model.server.ServerSettings;
import lombok.Getter;

public final class ServerController {

  @Getter
  public static final ServerSettings SETTINGS;

  static {
    SETTINGS = new ServerSettings();
  }

}
