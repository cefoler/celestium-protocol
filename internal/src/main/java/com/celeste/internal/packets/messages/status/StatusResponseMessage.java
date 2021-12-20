package com.celeste.internal.packets.messages.status;

import com.celeste.internal.annotation.Message;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.packets.entity.PlayerSimpleEntity;

import java.util.List;

@Message(id = 0x00)
public record StatusResponseMessage(String versionName, int protocol, int maximumPlayers, int currentPlayers,
                                    List<PlayerSimpleEntity> onlinePlayers,
                                    String description, String icon) implements PacketContent {

}
