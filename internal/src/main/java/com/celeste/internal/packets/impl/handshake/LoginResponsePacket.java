package com.celeste.internal.packets.impl.handshake;

import com.celeste.internal.packets.AbstractPacket;
import com.celeste.internal.packets.messages.ResponseMessage;
import com.celeste.internal.protocol.util.ProtocolBuffer;
import com.celeste.internal.util.JacksonAdapter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;

public final class LoginResponsePacket extends AbstractPacket<ResponseMessage> {

  public LoginResponsePacket() {
    super(null, 0x00);
  }

  @Override
  public Class<ResponseMessage> getMessage() {
    return ResponseMessage.class;
  }

  @Override
  public ResponseMessage read(ProtocolBuffer buffer) {
    return null;
  }

  @Override @SneakyThrows
  public void write(final ProtocolBuffer buffer, final ResponseMessage packet) {
    final JacksonAdapter adapter = JacksonAdapter.getInstance();
    final ObjectNode main = adapter.createNode();

    final ObjectNode version = adapter.createNode()
        .put("name", packet.getVersionName())
        .put("protocol", packet.getProtocol());

    final ObjectNode players = adapter.createNode()
        .put("max", packet.getMaximumPlayers())
        .put("online", packet.getCurrentPlayers())
        .put("sample", adapter.toJson(packet.getOnlinePlayers()));

    final ObjectNode description = adapter.createNode()
        .put("description", packet.getDescription());

    main.set("version", version);
    main.set("players", players);
    main.set("description", description);
    main.put("favicon", packet.getIcon());

    buffer.writeString(main.toPrettyString());
  }

}
