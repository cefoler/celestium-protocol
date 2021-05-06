package com.celeste.internal.model;

import com.celeste.internal.model.type.ConnectionState;
import com.celeste.minecraft.model.Location;
import com.celeste.minecraft.model.type.Gamemode;
import java.net.InetSocketAddress;
import java.util.UUID;

public interface Connection {

  UUID getId();
  String getName();

  ConnectionState getState();

  int getLatency();
  InetSocketAddress getAddress();

  int getProtocolVersion();
  long getFirstJoin();

  Location getLocation();
  Gamemode getGamemode();

}
