package com.celeste.internal.packets;

import com.celeste.internal.packets.messages.PacketMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;

public interface Packet<M extends PacketMessage> {

  Integer getInboundId();

  Integer getOutboundId();

  M read(final ProtocolBuffer buffer);

  <T extends PacketMessage> void write(final ProtocolBuffer buffer, T packet);

}
