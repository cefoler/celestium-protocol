package com.celeste.internal.model.protocol;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

/**
 * The ServerAddress represents the model used
 * to build the credentials to connect into the Server
 */
@Data
@Builder
public final class ServerAddress implements Serializable {

  /**
   * The address of the host, "localhost" can be used.
   */
  private final String host;
  /**
   * Port of the host, normally used as 25565
   */
  private final int port;

}
