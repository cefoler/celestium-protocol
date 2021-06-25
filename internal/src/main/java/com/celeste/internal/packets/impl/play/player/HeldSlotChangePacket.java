package com.celeste.internal.packets.impl.play.player;

import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.play.player.HeldSlotChangeMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;
import com.celeste.internal.registry.type.PlayPackets;

public final class HeldSlotChangePacket extends AbstractPacket<HeldSlotChangeMessage> {

  public HeldSlotChangePacket() {
    super(null, PlayPackets.HELD_SLOT_CHANGE.getOutboundId());
  }

  @Override
  public Class<HeldSlotChangeMessage> getMessage() {
    return HeldSlotChangeMessage.class;
  }

  @Override
  public HeldSlotChangeMessage read(ProtocolBuffer buffer) {
    return null;
  }

  @Override
  public void write(ProtocolBuffer buffer, HeldSlotChangeMessage packet) {
    buffer.getByteBuf().writeByte(packet.getSlot());
  }

}
