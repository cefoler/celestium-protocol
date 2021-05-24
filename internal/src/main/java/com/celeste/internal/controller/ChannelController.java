package com.celeste.internal.controller;

import com.celeste.internal.model.type.ConnectionState;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.packets.PacketHandler;
import com.celeste.internal.packets.handlers.HandshakeHandler;
import com.celeste.internal.registry.Protocol;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.SimpleChannelInboundHandler;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ChannelController extends SimpleChannelInboundHandler<PacketContent> {

  private Channel channel;

  private ConnectionState state;

  private int protocolVersion;
  private long creationTime;
  private boolean offlineMode;

  private boolean compression;

  private PacketHandler handler;
  private Protocol protocol = Protocol.INSTANCE;

  public ChannelController(final Channel channel) {
    this.channel = channel;
    this.state = ConnectionState.HANDSHAKE;
    this.handler = new HandshakeHandler(this);
    this.offlineMode = false;
    this.compression = false;
  }

  /**
   * Reads a PacketContent received to this channel
   * @param channelHandlerContext ChannelHandlerContext
   * @param packetContent PacketContent
   */
  @Override
  protected void channelRead0(final ChannelHandlerContext channelHandlerContext, final PacketContent packetContent) {
    // The handler is changed event after event so it can fit properly to the content
    handler.read(channelHandlerContext, packetContent);
  }

  /**
   * Dispatchs a packet into the channel
   * @param packet PacketContent
   */
  public void dispatch(final PacketContent packet) {
    channel.writeAndFlush(packet);
  }

}
