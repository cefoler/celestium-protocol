package com.celeste.internal.packets.impl.handshake;

import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.status.StatusResponseMessage;
import com.celeste.internal.protocol.utils.ProtocolBuffer;
import com.celeste.internal.registry.type.LoginPackets;
import com.celeste.library.core.adapter.impl.jackson.JacksonAdapter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;

public final class LoginResponsePacket extends AbstractPacket<StatusResponseMessage> {

  public LoginResponsePacket() {
    super(null, LoginPackets.RESPONSE.getOutboundId());
  }

  @Override
  public Class<StatusResponseMessage> getMessage() {
    return StatusResponseMessage.class;
  }

  @Override
  public StatusResponseMessage read(ProtocolBuffer buffer) {
    return null;
  }

  @Override @SneakyThrows
  public void write(final ProtocolBuffer buffer, final StatusResponseMessage packet) {
    final JacksonAdapter adapter = JacksonAdapter.getInstance();
    final ObjectNode main = adapter.createNode();

    final ObjectNode version = adapter.createNode()
        .put("name", packet.getVersionName())
        .put("protocol", packet.getProtocol());

    final ObjectNode players = adapter.createNode()
        .put("max", packet.getMaximumPlayers())
        .put("online", packet.getCurrentPlayers())
        .put("sample", adapter.serialize(packet.getOnlinePlayers()));

    final ObjectNode description = adapter.createNode()
        .put("description", packet.getDescription());

    main.set("version", version);
    main.set("players", players);
    main.set("description", description);
    main.put("favicon", packet.getIcon());

    buffer.writeString(main.toPrettyString());
  }

}
