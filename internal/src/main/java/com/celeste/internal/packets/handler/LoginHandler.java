package com.celeste.internal.packets.handler;

import com.celeste.internal.controller.ChannelController;
import com.celeste.internal.exception.protocol.PacketException;
import com.celeste.internal.model.player.impl.PlayerConnection;
import com.celeste.internal.model.protocol.ConnectionState;
import com.celeste.internal.model.protocol.state.LoginState;
import com.celeste.internal.packets.PacketContent;
import com.celeste.internal.packets.PacketHandler;
import com.celeste.internal.packets.handler.play.KeepAliveHandler;
import com.celeste.internal.packets.handler.play.PlayHandler;
import com.celeste.internal.packets.messages.login.LoginStartMessage;
import com.celeste.internal.packets.messages.login.LoginSuccessMessage;
import com.celeste.internal.packets.messages.play.JoinMessage;
import com.celeste.internal.packets.messages.play.player.PlayerAbilitiesMessage;
import com.celeste.internal.packets.messages.play.world.DifficultyMessage;
import com.celeste.internal.registry.ConnectionRegistry;
import com.celeste.internal.registry.KeepAliveRegistry;
import com.celeste.library.core.util.Logger;
import com.celeste.game.model.Location;
import com.celeste.game.model.type.Gamemode;
import com.mojang.authlib.GameProfile;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class LoginHandler extends PacketHandler {

  public LoginHandler(final ChannelController controller) {
    super(controller);
    this.loginState = LoginState.START;
  }

  private LoginState loginState;

  private UUID id;
  private String username;

  @Override
  public void read(final ChannelHandlerContext context, final PacketContent message) {
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

        ConnectionRegistry.INSTANCE.register(id, playerConnection);

        getController().setState(ConnectionState.PLAY);
        getController().setHandler(new PlayHandler(getController()));

        KeepAliveRegistry.INSTANCE.register(id, new KeepAliveHandler(getController()));

        dispatch(
            new LoginSuccessMessage(id, username),
            new JoinMessage(),
            new DifficultyMessage(),
            new PlayerAbilitiesMessage()
        );
      }
      default -> throw new PacketException("A invalid LoginState has been received.");
    }
  }

}
