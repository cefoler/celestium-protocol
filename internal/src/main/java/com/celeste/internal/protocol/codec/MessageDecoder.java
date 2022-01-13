package com.celeste.internal.protocol.codec;

import com.celeste.internal.controller.ChannelController;
import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.Packet;
import com.celeste.internal.packets.messages.PacketMessage;
import com.celeste.internal.protocol.utils.PacketFormatter;
import com.celeste.internal.protocol.utils.ProtocolBuffer;
import com.celeste.internal.registry.Protocol;
import com.celeste.library.core.util.Logger;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;
import lombok.AllArgsConstructor;

import java.util.List;

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
    final int packetId = buffer.readVarInt();

    final Packet<?> packet = Protocol.getPacketInbound(controller.getState(), packetId);
    if (packet == null) {
      Logger.getLogger().atSevere().log("A packet with unidentified id has been received: " + PacketFormatter.format(packetId));
      return;
    }

    final PacketMessage content = packet.read(buffer);
    list.add(content);

    Logger.getLogger().atInfo().log("Received packet with ID: " + PacketFormatter.format(packetId));
  }

  @Override
  public void exceptionCaught(final ChannelHandlerContext context, final Throwable cause) {
    Logger.getLogger().atWarning().log(cause.getMessage());
  }

}
