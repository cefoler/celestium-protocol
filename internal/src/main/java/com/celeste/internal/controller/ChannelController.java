package com.celeste.internal.controller;

import com.celeste.internal.model.type.ConnectionState;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.packets.PacketHandler;
import com.celeste.internal.packets.handlers.HandshakeHandler;
import com.celeste.internal.registry.Protocol;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.SimpleChannelInboundHandler;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ChannelController extends SimpleChannelInboundHandler<PacketContent> {

  private ConnectionState state;

  private int protocolVersion;
  private long creationTime;

  private Protocol protocol = Protocol.INSTANCE;

  private PacketHandler handler;

  public ChannelController() {
    this.state = ConnectionState.HANDSHAKE;
    this.handler = new HandshakeHandler(this);
  }

  @Override
  protected void channelRead0(final ChannelHandlerContext channelHandlerContext, final PacketContent packetContent) {
    // The handler is changed event after event so it can fit properly to the content
    handler.read(channelHandlerContext, packetContent);
  }

}
