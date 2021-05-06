package com.celeste.internal.model.type;

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
  /**
   * Client side connection is current sending
   * and receiving packets for the Handshake
   * and the Login event.
   */
  HANDSHAKE,

}
