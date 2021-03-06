package com.celeste.internal.packets.handler.impl;

import com.celeste.game.model.Location;
import com.celeste.game.model.type.Gamemode;
import com.celeste.internal.controller.ChannelController;
import com.celeste.internal.exception.protocol.PacketException;
import com.celeste.internal.model.player.impl.PlayerConnection;
import com.celeste.internal.model.protocol.ConnectionState;
import com.celeste.internal.model.protocol.state.LoginState;
import com.celeste.internal.packets.messages.PacketMessage;
import com.celeste.internal.packets.handler.AbstractPacketHandler;
import com.celeste.internal.packets.handler.impl.play.KeepAliveHandler;
import com.celeste.internal.packets.handler.impl.play.PlayHandler;
import com.celeste.internal.packets.messages.login.LoginStartMessage;
import com.celeste.internal.packets.messages.login.LoginSuccessMessage;
import com.celeste.internal.packets.messages.play.JoinMessage;
import com.celeste.internal.packets.messages.play.player.PlayerAbilitiesMessage;
import com.celeste.internal.packets.messages.play.world.DifficultyMessage;
import com.celeste.internal.registry.Registries;
import com.celeste.library.core.util.Logger;
import com.mojang.authlib.GameProfile;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.Setter;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Getter
@Setter
public final class LoginHandler extends AbstractPacketHandler {

  private LoginState loginState;
  private UUID id;
  private String username;

  public LoginHandler(final ChannelController controller) {
    super(controller);
    this.loginState = LoginState.START;
  }

  @Override
  public void read(final ChannelHandlerContext context, final PacketMessage message) {
    switch (loginState) {
      case START -> {
        System.out.println("LOGIN START");
        final LoginStartMessage handshake = (LoginStartMessage) message;
        this.username = handshake.username();

        if (getController().isOfflineMode()) {
          Logger.getLogger().atWarning().log("Server is currently at Offline mode. Initiating without Encryption.");

          setLoginState(LoginState.SUCCESS);
          return;
        }

        setLoginState(LoginState.ENCRYPTION_REQUEST);
      }
      case ENCRYPTION_REQUEST -> {
        // TODO: Encryption request
      }
      case ENCRYPTION_RESPONSE -> {
        // TODO: Encryption response
      }
      case SUCCESS -> {
        System.out.println("LOGIN SUCCESS");
        // Handle online mode
        if (getController().isOfflineMode()) {
          this.id = UUID.nameUUIDFromBytes(("celestium-player=" + username).getBytes(StandardCharsets.UTF_8));
        }

        final PlayerConnection playerConnection = PlayerConnection.builder()
            .gameProfile(new GameProfile(id, username))
            .state(ConnectionState.PLAY)
            .address((InetSocketAddress) context.channel().remoteAddress())
            .firstJoin(System.currentTimeMillis())
            .gamemode(Gamemode.SURVIVAL)
            .latency(0)
            .location(new Location())
            .protocolVersion(getController().getProtocolVersion())
            .build();

        Registries.CONNECTION.register(id, playerConnection);

        getController().setState(ConnectionState.PLAY);
        getController().setHandler(new PlayHandler(getController()));

        Registries.KEEP_ALIVE.register(id, new KeepAliveHandler(getController()));

        dispatch(
            new LoginSuccessMessage(id, username),
            new JoinMessage(),
            new DifficultyMessage(),
            new PlayerAbilitiesMessage()
        );
      }
      default -> throw new PacketException("A invalid LoginState has been received. ID: " + message.getId());
    }
  }

}
