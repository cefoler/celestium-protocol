package com.celeste.internal.model.player;

import com.celeste.game.model.Location;
import com.celeste.game.model.type.Gamemode;
import com.celeste.internal.model.protocol.ConnectionState;
import com.celeste.internal.model.protocol.ProtocolVersion;

import java.net.InetSocketAddress;
import java.util.UUID;

public interface Connection {

  UUID getId();

  String getName();

  ConnectionState getState();

  int getLatency();

  InetSocketAddress getAddress();

  ProtocolVersion getProtocolVersion();

  long getFirstJoin();

  Location getLocation();

  Gamemode getGamemode();

}
