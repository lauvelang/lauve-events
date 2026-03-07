package lol.sylvie.lauve.events.api;

import lombok.Getter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class Listener<T extends Event> {
    @Getter
    private final Class<T> eventClass;

    @SuppressWarnings("unchecked")
    public Listener() {
        final Type clazz = this.getClass().getGenericSuperclass();
        this.eventClass = (Class<T>) (clazz instanceof ParameterizedType
                ? ((ParameterizedType) clazz).getActualTypeArguments()[0]
                : Event.class);
    }

    public Listener(final Class<T> eventClass) {
        this.eventClass = eventClass;
    }

    public byte getPriority() {
        return 0;
    }

    public abstract void call(T event);
}
