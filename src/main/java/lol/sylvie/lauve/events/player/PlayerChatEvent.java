package lol.sylvie.lauve.events.player;

import lol.sylvie.lauve.events.api.CancellableEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.level.ServerPlayer;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Set;

/**
 * Fired when a chat message is broadcasted. This includes both system and player messages.
 *
 * @see lol.sylvie.lauve.events.mixin.player.PlayerListMixin
 * @author sylvie <3
 *
 */
@AllArgsConstructor
@Getter
public class PlayerChatEvent extends CancellableEvent {
    @Setter
    PlayerChatMessage message;
    @Nullable ServerPlayer player;
    ChatType.Bound bound;
    Set<ServerPlayer> recipients;
}
