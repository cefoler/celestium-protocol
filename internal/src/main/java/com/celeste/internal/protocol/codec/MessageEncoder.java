package com.celeste.internal.protocol.codec;

import com.celeste.internal.controller.ChannelController;
import com.celeste.internal.exception.PacketException;
import com.celeste.internal.packets.Packet;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.protocol.util.ProtocolBuffer;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class MessageEncoder extends MessageToByteEncoder<PacketContent> {

  private final ChannelController controller;

  @Override
  protected void encode(ChannelHandlerContext channelHandlerContext, PacketContent packetContent, ByteBuf byteBuf) {
    final Packet packet = controller.getProtocol().getPacketOutbound(controller.getState(), packetContent.getClass());
    if (packet == null) {
      throw new PacketException("A packet with unidentified id has been tried to sent. Name: " + packetContent.getClass().getSimpleName());
    }

    final ProtocolBuffer buffer = new ProtocolBuffer(byteBuf);

    buffer.writeVarInt(packet.getOutboundId());
    packet.write(buffer, packetContent);
  }

}
