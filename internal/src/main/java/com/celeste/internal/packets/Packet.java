package com.celeste.internal.packets;

import com.celeste.internal.protocol.util.ProtocolBuffer;
import com.celeste.internal.registry.Protocol;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>The packet is the major information
 * transmitter for the CLIENT and SERVER side,
 * it contains three informations:
 *
 * <p>Length: Size of the protocol version + data bytes
 * <p>Packet ID: ID of the packet to indentify it's type,
 * the types can be found at {@link Protocol}
 * <p>Data: Array of bytes of the total data received
 */
@Data
@AllArgsConstructor
public abstract class Packet<Message extends PacketContent> {

  private Integer inboundId;
  private Integer outboundId;

  public abstract Class<Message> getMessage();

  /**
   * Reads the received packet bytes sent through
   * the ByteBuf of the Netty channel
   *
   * <p>The ProtocolBuffer should be created with that
   * ByteBuf, otherwise it won't work.</p>
   *
   * After the packet has been read, it trigger
   * the EventBus to use listeners in the API
   *
   * @param buffer ProtocolBuffer
   */
  public abstract Message read(final ProtocolBuffer buffer);

  /**
   * Writes the response through the ByteBuf
   * in the ProtocolBuffer, after that, Netty
   * receives that response and sends to the SERVER.
   *
   * <p>This response is automatically write by
   * the individual packet provider</p>
   *
   * @param buffer ProtocolBuffer
   */
  public abstract void write(final ProtocolBuffer buffer, Message packet);

}
