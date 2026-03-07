package lol.sylvie.lauve.events.api;

import lol.sylvie.lauve.events.LauveEvents;

import java.util.*;

public class EventBus {
    private static final List<Listener<?>> buffer = new ArrayList<>();
    private static final Map<Class<? extends Event>, Listener<?>[]> eventMap = new HashMap<>();

    public static void subscribe(Listener<?> listener) {
        final Listener<?>[] listeners = eventMap.get(listener.getEventClass());
        if (listeners != null) buffer.addAll(Arrays.asList(listeners));

        buffer.add(listener);
        if (listener.getPriority() != 0) buffer.sort(Collections.reverseOrder(Comparator.comparingInt(Listener::getPriority)));

        eventMap.put(listener.getEventClass(), buffer.toArray(new Listener[0]));
        buffer.clear();
    }

    public static void unsubscribe(Listener<?> listener) {
        final Listener<?>[] listeners = eventMap.get(listener.getEventClass());
        if (listeners == null) return;

        for (Listener<?> otherListener : listeners) {
            if (listener == otherListener) continue;
            buffer.add(listener);
        }

        eventMap.put(listener.getEventClass(), buffer.toArray(new Listener[0]));
        buffer.clear();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Event> T post(T event) {
        if (System.getProperties().containsKey("LAUVE_EVENTS_DEBUG_LOGS")) {
            LauveEvents.LOGGER.info("Posting {}", event.getClass().getSimpleName());
        }

        final Listener<T>[] list = (Listener<T>[]) eventMap.get(event.getClass());
        if (list == null) return event;

        for (Listener<T> listener : list) {
            listener.call(event);
        }

        return event;
    }
}
