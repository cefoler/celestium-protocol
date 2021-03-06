package com.celeste.internal.packets;

import com.celeste.internal.annotation.PacketInfo;
import com.celeste.internal.model.protocol.ProtocolDirection;
import com.celeste.internal.packets.messages.PacketMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;
import lombok.Data;
import lombok.SneakyThrows;

import java.lang.reflect.ParameterizedType;

/**
 * <p>The packet is the major information
 * transmitter for the {@link ProtocolDirection} messages,
 * it contains three informations:
 *
 * <p>Length: Size of the protocol version + data bytes
 * <p>Packet ID: ID of the packet to indentify it's type
 * <p>Data: Array of bytes of the total data received
 *
 * <p>A packet ID can have two different types, if it's sent
 * by the server, the ID is Inbound. And if it's sent by the
 * client, it's Outbound</p>
 */
@Data
public abstract class AbstractPacket<M extends PacketMessage> implements Packet<M> {

  private Integer inboundId;
  private Integer outboundId;

  private Class<M> messageClass;

  @SneakyThrows
  @SuppressWarnings("unchecked")
  public AbstractPacket() {
    final PacketInfo packet = getClass().getAnnotation(PacketInfo.class);
    if (packet == null) {
      return;
    }

    this.inboundId = packet.inboundId();
    this.outboundId = packet.outboundId();

    // Gets the M class by Reflection as Java doesn't allows it
    final ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
    this.messageClass = (Class<M>) Class.forName(parameterizedType.getActualTypeArguments()[0].toString());
  }

  /**
   * Reads the received packet bytes sent through
   * the ByteBuf of the Netty channel
   *
   * <p>The ProtocolBuffer should be created with that
   * ByteBuf, otherwise it won't work.</p>
   * <p>
   * After the packet has been read, it trigger
   * the EventBus to use listeners in the API
   *
   * @param buffer ProtocolBuffer
   */
  public M read(final ProtocolBuffer buffer) {
    return null;
  }

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
  public void write(final ProtocolBuffer buffer, M packet) {}

}
