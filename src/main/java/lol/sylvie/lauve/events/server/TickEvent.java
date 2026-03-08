package lol.sylvie.lauve.events.server;

import lol.sylvie.lauve.events.api.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.server.MinecraftServer;


@AllArgsConstructor
@Getter
public abstract class TickEvent implements Event {
    private final MinecraftServer server;

    /**
     * Fired at the very start of the server tick
     *
     * @see lol.sylvie.lauve.events.mixin.server.MinecraftServerMixin
     * @author sylvie <3
     *
     */
    public static class Start extends TickEvent {
        public Start(MinecraftServer server) {
            super(server);
        }
    }

    /**
     * Fired at the very end of the server tick
     *
     * @see lol.sylvie.lauve.events.mixin.server.MinecraftServerMixin
     * @author sylvie <3
     *
     */
    public static class End extends TickEvent {
        public End(MinecraftServer server) {
            super(server);
        }
    }
}
