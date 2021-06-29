package com.celeste.internal.protocol.codec;

import com.celeste.internal.controllers.ChannelController;
import com.celeste.internal.exceptions.protocol.PacketException;
import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.protocol.utils.ProtocolBuffer;
import com.celeste.internal.registry.Protocol;
import com.celeste.library.core.util.Logger;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import lombok.AllArgsConstructor;

/**
 * <p>Packet decoder for the main Netty pipeline on the bootstrap.
 *
 * <p>It receives the packets from the server and
 * sends a formatted {@link AbstractPacket} to the
 * {@link ChannelController}
 */
@AllArgsConstructor
public final class MessageDecoder extends ByteToMessageDecoder {

  private final ChannelController controller;

  protected void decode(final ChannelHandlerContext context, final ByteBuf bytebuf, final List<Object> list) {
    final ProtocolBuffer buffer = new ProtocolBuffer(bytebuf);
    int packetId = buffer.readVarInt();

    final AbstractPacket<?> packet = Protocol.INSTANCE.getPacketInbound(controller.getState(), packetId);
    if (packet == null) {
      Logger.getLogger().atSevere().log("A packet with unidentified id has been received: " + packetId);
      return;
    }

    final PacketContent content = packet.read(buffer);
    list.add(content);

    Logger.getLogger().atInfo().log("Received packet with ID: " + content.getId());
  }

  @Override
  public void exceptionCaught(final ChannelHandlerContext context, final Throwable cause) {
    Logger.getLogger().atWarning().log(cause.getMessage());
  }

}
