package com.celeste.internal.controller;

import com.celeste.internal.model.PacketContent;
import com.celeste.internal.model.PacketHandler;
import com.celeste.internal.model.protocol.Protocol;
import com.celeste.internal.model.protocol.type.ConnectionState;
import com.celeste.internal.registry.ChannelRegistry;
import com.celeste.internal.registry.ProtocolRegistry;
import com.celeste.internal.views.handlers.HandshakeHandler;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
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
    handler.readPacket(channelHandlerContext, packetContent);
  }

}
