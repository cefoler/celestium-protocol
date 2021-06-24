package com.celeste.internal.packets.messages.play;

import com.celeste.internal.packets.PacketContent;
import com.celeste.minecraft.model.type.Gamemode;
import com.celeste.minecraft.model.world.World;
import lombok.Data;

@Data
public final class JoinMessage implements PacketContent {

  private final int entityId;
  private final boolean hardcore;
  private final Gamemode gamemode;

  private final World world;
  private final int viewDistance;

  private final boolean debug;
  private final boolean respawnScreen;

}
