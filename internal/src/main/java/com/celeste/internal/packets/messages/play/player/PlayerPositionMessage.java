package com.celeste.internal.packets.messages.play.player;

import com.celeste.internal.annotation.Message;
import com.celeste.internal.packets.messages.PacketMessage;

@Message(id = 0x38)
public record PlayerPositionMessage(double x, double y, double z, float yaw, float pitch, int flag, int teleportId,
                                    boolean dismount) implements PacketMessage {

}
