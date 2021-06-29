package com.celeste.internal.controllers;

import com.celeste.internal.model.server.ServerProperties;
import com.celeste.internal.model.server.ServerSettings;
import lombok.Getter;

public final record ServerController() {

  public static final ServerSettings SETTINGS;
  public static final ServerProperties PROPERTIES;

  public static final long LAST_ENTITY_ID;

  static {
    SETTINGS = new ServerSettings();
    PROPERTIES = new ServerProperties();
    LAST_ENTITY_ID = 0;
  }

}
