package lol.sylvie.lauve.events.server;

import lol.sylvie.lauve.events.api.Event;
import net.minecraft.server.MinecraftServer;

/**
 * Fired at the very start of the server tick
 *
 * @see lol.sylvie.lauve.events.mixin.server.MinecraftServerMixin
 * @author sylvie <3
 *
 */
public record StartTickEvent(MinecraftServer server) implements Event {}