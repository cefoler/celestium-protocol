package com.celeste.internal.controller;

import com.celeste.internal.model.protocol.ConnectionState;
import com.celeste.internal.model.protocol.ProtocolVersion;
import com.celeste.internal.packets.messages.PacketMessage;
import com.celeste.internal.packets.handler.PacketHandler;
import com.celeste.internal.packets.handler.impl.HandshakeHandler;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.SimpleChannelInboundHandler;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ChannelController extends SimpleChannelInboundHandler<PacketMessage> {

  private final Channel channel;

  private final long creationTime;
  private ConnectionState state;

  private PacketHandler handler;

  private ProtocolVersion protocolVersion;
  private boolean offlineMode;

  private boolean compression;

  public ChannelController(final Channel channel) {
    this.channel = channel;
    this.state = ConnectionState.HANDSHAKE;
    this.handler = new HandshakeHandler(this);
    this.offlineMode = false;
    this.compression = false;
    this.creationTime = System.currentTimeMillis();
  }

  /**
   * Reads a PacketContent received to this channel
   *
   * @param context       ChannelHandlerContext
   * @param packetMessage PacketContent
   */
  @Override
  protected void channelRead0(final ChannelHandlerContext context, final PacketMessage packetMessage) {
    // The handler is changed event after event so it can fit properly to the content
    handler.read(context, packetMessage);
  }

  /**
   * Dispatchs a packet into the channel
   *
   * @param packet PacketContent
   */
  public void dispatch(final PacketMessage packet) {
    if (isOpen()) {
      channel.writeAndFlush(packet);
    }
  }

  public boolean isOpen() {
    return channel != null && channel.isOpen();
  }

}
