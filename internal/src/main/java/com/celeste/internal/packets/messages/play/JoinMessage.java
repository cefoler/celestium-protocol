package com.celeste.internal.packets.messages.play;

import com.celeste.internal.annotation.Message;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.registry.type.PlayPackets;
import com.celeste.game.model.type.Gamemode;
import com.celeste.game.model.world.World;
import lombok.Data;

import static com.celeste.internal.controller.ServerController.*;

@Data
@Message(id = 0x26)
public final class JoinMessage implements PacketContent {

  private final int entityId;
  private final boolean hardcore;
  private final Gamemode gamemode;

  private final World world;
  private final int viewDistance;

  private final boolean debug;
  private final boolean respawnScreen;

  public JoinMessage() {
    this.entityId = (int) (LAST_ENTITY_ID + 1);
    this.hardcore = PROPERTIES.isHardcore();
    this.gamemode = PROPERTIES.getGamemode();
    this.world = SETTINGS.getDefaultWorld();
    this.viewDistance = PROPERTIES.getViewDistance();
    this.debug = PROPERTIES.isDebug();
    this.respawnScreen = false;
  }

}
