package lol.sylvie.lauve.events.mixin.player;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import lol.sylvie.lauve.events.api.EventBus;
import lol.sylvie.lauve.events.player.PlayerChatEvent;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Mixin(PlayerList.class)
public class PlayerListMixin {
    @Shadow
    @Final
    private List<ServerPlayer> players;

    @WrapMethod(method = "broadcastChatMessage(Lnet/minecraft/network/chat/PlayerChatMessage;Ljava/util/function/Predicate;Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/network/chat/ChatType$Bound;)V")
    public void levents$broadcastChatMessage(PlayerChatMessage message, Predicate<ServerPlayer> predicate, @Nullable ServerPlayer player, ChatType.Bound bound, Operation<Void> original) {
        // FIXME: The game already loops over the players to see who to send it to, so I don't like doing it again here.
        Set<ServerPlayer> recipients = players.stream().filter(predicate).collect(Collectors.toSet());

        PlayerChatEvent event = EventBus.post(new PlayerChatEvent(message, player, bound, recipients));
        original.call(event.getMessage(), (Predicate<ServerPlayer>) serverPlayer -> event.getRecipients().contains(serverPlayer), player, bound);
    }
}
