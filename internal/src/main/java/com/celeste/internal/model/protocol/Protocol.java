package com.celeste.internal.model.protocol;

import com.celeste.internal.registry.ProtocolRegistry;
import com.celeste.internal.model.Packet;
import com.celeste.internal.model.PacketContent;
import com.celeste.internal.model.packets.HandshakePacket;
import com.celeste.internal.model.protocol.type.ConnectionState;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The protocol contains all registered
 * packets with it's values:
 *
 * <p>Packet ID: ID of each packet</p>
 * <p>Direction: Direction of the Packet, can be CLIENT or SERVER</p>
 * <p>Packet: Class of the provider of the packet</p>
 */
@Getter
public final class Protocol {

    public static final Protocol INSTANCE = new Protocol();

    /**
     * Get the Packet by the ID and Stage
     * @param packetId Integer
     *
     * @return Protocol
     */
    public Packet<? extends PacketContent> getPacket(final ConnectionState stage, final int packetId) {
        switch(stage) {
            case HANDSHAKE: {
                return new HandshakePacket();
            }
            case STATUS: return filter(ProtocolRegistry.INSTANCE.getStatusPackets(), packetId);
            case LOGIN: return filter(ProtocolRegistry.INSTANCE.getLoginPackets(), packetId);
            case PLAY: return filter(ProtocolRegistry.INSTANCE.getPlayPackets(), packetId);
            default: return null;
        }
    }

    private Packet<?> filter(Map<Class<? extends PacketContent>, Packet> map, int id) {
        return map.values().stream()
            .filter(packet -> packet.getId() != null)
            .filter(packet -> packet.getId() == id)
            .findFirst()
            .orElse(null);
    }

}
