package com.celeste.internal.registry.type;

import com.celeste.internal.packets.Packet;
import com.celeste.internal.packets.messages.PacketMessage;
import com.celeste.internal.packets.impl.play.JoinPacket;
import com.celeste.internal.packets.impl.play.KeepAlivePacket;
import com.celeste.internal.packets.impl.play.player.HeldSlotChangePacket;
import com.celeste.internal.packets.impl.play.player.PlayerAbilitiesPacket;
import com.celeste.internal.packets.impl.play.player.PlayerPositionPacket;
import com.celeste.internal.packets.impl.play.world.DifficultyPacket;
import com.celeste.internal.packets.messages.play.JoinMessage;
import com.celeste.internal.packets.messages.play.KeepAliveMessage;
import com.celeste.internal.packets.messages.play.player.HeldSlotChangeMessage;
import com.celeste.internal.packets.messages.play.player.PlayerAbilitiesMessage;
import com.celeste.internal.packets.messages.play.player.PlayerPositionMessage;
import com.celeste.internal.packets.messages.play.world.DifficultyMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PlayPackets {

  KEEP_ALIVE(null, 0x10, KeepAliveMessage.class, new KeepAlivePacket()),
  JOIN(null, 0x26, JoinMessage.class, new JoinPacket()),
  DIFFICULTY(null, 0x0E, DifficultyMessage.class, new DifficultyPacket()),
  PLAYER_POSITION_AND_LOOK(null, 0x38, PlayerPositionMessage.class, new PlayerPositionPacket()),
  PLAYER_ABILITIES(0x32, 0x32, PlayerAbilitiesMessage.class, new PlayerAbilitiesPacket()),
  HELD_SLOT_CHANGE(null, 0x48, HeldSlotChangeMessage.class, new HeldSlotChangePacket());

  private final Integer inboundId;
  private final Integer outboundId;
  private final Class<? extends PacketMessage> message;
  private final Packet<? extends PacketMessage> packet;

  public static PlayPackets getByMessage(Class<? extends PacketMessage> message) {
    return Arrays.stream(values())
        .filter(packets -> packets.getMessage() == message)
        .findFirst()
        .orElse(null);
  }

  public static PlayPackets getInbound(int id) {
    return Arrays.stream(values())
        .filter(packets -> packets.getInboundId() == id)
        .findFirst()
        .orElse(null);
  }

  public static PlayPackets getOutbound(int id) {
    return Arrays.stream(values())
        .filter(packets -> packets.getOutboundId() == id)
        .findFirst()
        .orElse(null);
  }

}
