package com.celeste.internal.packets.impl.play.player;

import com.celeste.internal.annotation.PacketInfo;
import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.play.player.HeldSlotChangeMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;

@PacketInfo(outboundId = 0x48)
public final class HeldSlotChangePacket extends AbstractPacket<HeldSlotChangeMessage> {

  @Override
  public void write(final ProtocolBuffer buffer, final HeldSlotChangeMessage packet) {
    buffer.getByteBuf().writeByte(packet.slot());
  }

}
