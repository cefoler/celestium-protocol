package com.celeste.internal.model.protocol;

import lombok.Getter;

/**
 * Current status of the connection
 * between the Netty client and the protocol
 * from the server.
 */
@Getter
public enum ConnectionState {

  PLAY,
  LOGIN,
  STATUS,
  HANDSHAKE

}
