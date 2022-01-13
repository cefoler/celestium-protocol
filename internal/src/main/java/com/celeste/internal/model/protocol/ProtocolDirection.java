package com.celeste.internal.model.protocol;

import lombok.Getter;

/**
 * Direction sent in packets, can only be
 * two types
 */
@Getter
public enum ProtocolDirection {

  /**
   * Indicated that the packet is sent by the server
   */
  SERVER,
  /**
   * Indicated that the packet is sent by the player client
   */
  CLIENT

}
