package com.celeste.internal.packets.messages;

import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.packets.entity.PlayerSimpleEntity;
import lombok.Data;

import java.util.List;

@Data
public final class ResponseMessage implements PacketContent {

  private final String versionName;
  private final int protocol;

  private final int maximumPlayers;
  private final int currentPlayers;
  private final List<PlayerSimpleEntity> onlinePlayers;

  private final String description;
  private final String icon;

}
