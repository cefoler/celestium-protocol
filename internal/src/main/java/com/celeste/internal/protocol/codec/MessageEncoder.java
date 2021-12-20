package com.celeste.internal.protocol.codec;

import com.celeste.internal.controller.ChannelController;
import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.protocol.utils.ProtocolBuffer;
import com.celeste.internal.registry.Protocol;
import com.celeste.library.core.util.Logger;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class MessageEncoder extends MessageToByteEncoder<PacketContent> {

  private final ChannelController controller;

  @Override @SuppressWarnings("unchecked")
  protected void encode(final ChannelHandlerContext channelHandlerContext, final PacketContent packetContent, final ByteBuf byteBuf) {
    if (packetContent == null) {
      Logger.getLogger().atSevere().log("A null packet has been tried to sent.");
      return;
    }

    if (packetContent.getId() == 940682680) {
      Logger.getLogger().atSevere().log("A invalid packet has been tried to sent.");
      return;
    }

    final AbstractPacket packet = Protocol.getPacketOutbound(controller.getState(), packetContent.getId());
    if (packet == null) {
      Logger.getLogger().atSevere().log("A packet with unidentified id has been tried to sent. Name: " + packetContent.getClass().getSimpleName());
      return;
    }

    if (packet.getOutboundId() == 999999) {
      return;
    }

    final ProtocolBuffer buffer = new ProtocolBuffer(byteBuf);
    buffer.writeVarInt(packet.getOutboundId());
    packet.write(buffer, packetContent);

    Logger.getLogger().atInfo().log("Send packet with content: " + packetContent);
  }

}
