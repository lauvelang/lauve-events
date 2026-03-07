package lol.sylvie.lauve.events;

import lol.sylvie.lauve.events.api.Event;
import lol.sylvie.lauve.events.entity.AreaEffectCloudApplyEvent;
import lol.sylvie.lauve.events.entity.BodyArrowCountChangeEvent;
import lol.sylvie.lauve.events.player.PlayerChatEvent;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LauveEvents {
	public static final Logger LOGGER = LoggerFactory.getLogger("lauve-events");

	public static Map<String, Class<? extends Event>> EVENTS = new HashMap<>();

	public static void add(String name, Class<? extends Event> clazz) {
		EVENTS.put(name, clazz);
	}

	static {
		add("an area effect cloud applies", AreaEffectCloudApplyEvent.class);
		add("an entity's body arrow count changes", BodyArrowCountChangeEvent.class);
		add("a player chats", PlayerChatEvent.class);
	}
}