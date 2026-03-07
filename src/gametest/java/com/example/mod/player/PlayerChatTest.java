package com.example.mod.player;

import com.example.mod.EventTest;
import lol.sylvie.lauve.events.player.PlayerChatEvent;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.network.chat.*;
import net.minecraft.network.protocol.game.ServerboundChatPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.commands.KickCommand;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;

import java.lang.reflect.Method;
import java.time.Instant;
import java.util.BitSet;

// FIXME: Is there a way to spawn in a fake, secure player?
public class PlayerChatTest extends EventTest<PlayerChatEvent> {
    private ServerPlayer player;

    public PlayerChatTest() {
        super(1);
    }

    @Override
    public void handleEvent(PlayerChatEvent event) {
        assert event.getPlayer() != null;
        event.setMessage(PlayerChatMessage.unsigned(event.getPlayer().getUUID(), "Goodbye world!")); // FIXME: This isn't checked
    }

    @Override
    @SuppressWarnings({"removal"})
    public void invokeTestMethod(GameTestHelper context, Method method) throws ReflectiveOperationException {
        super.invokeTestMethod(context, method);

        this.player = context.makeMockServerPlayerInLevel();

        MinecraftServer server = context.getLevel().getServer();
        context.assertFalse(server.enforceSecureProfile(), "Server must be insecure for this test to pass!");

        this.player.connection.handleChat(new ServerboundChatPacket(
            "Hello world!",
                Instant.now(),
                0L,
                null,
                new LastSeenMessages.Update(0, BitSet.valueOf(new byte[0]), (byte) 0x00)
        ));
        method.invoke(this, context);
    }

    @Override
    protected void checkSuccessful(GameTestHelper context) {
        this.player.connection.disconnect(Component.empty());
        super.checkSuccessful(context);
    }
}
