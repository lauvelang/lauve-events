package com.example.mod;

import lol.sylvie.lauve.events.api.Event;
import lol.sylvie.lauve.events.api.EventBus;
import lol.sylvie.lauve.events.api.Listener;
import net.fabricmc.fabric.api.gametest.v1.CustomTestMethodInvoker;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class EventTest<T extends Event> implements CustomTestMethodInvoker {
    private final Class<T> clazz;
    private boolean invoked = false;
    private final Listener<T> listener;

    private int checkAfter = 0;

    @SuppressWarnings("unchecked")
    public EventTest() {
        final Type thisClass = this.getClass().getGenericSuperclass();
        this.clazz = (Class<T>) (thisClass instanceof ParameterizedType
                ? ((ParameterizedType) thisClass).getActualTypeArguments()[0]
                : Event.class);

        this.listener = new Listener<>(this.clazz) {
            @Override
            public void call(T event) {
                invoked = true;
                handleEvent(event);
            }
        };
    }

    public EventTest(int checkAfter) {
        this();
        this.checkAfter = checkAfter;
    }

    public void handleEvent(T event) {}

    protected void checkSuccessful(GameTestHelper context) {
        context.assertTrue(invoked, "Event " + clazz.getName() + " was not called");
    }

    private void prepareCheck(GameTestHelper context) {
        EventBus.unsubscribe(this.listener);
        checkSuccessful(context);
        context.succeed();
    }

    @GameTest
    public void test(GameTestHelper context) {
        if (checkAfter > 0) {
            context.runAfterDelay(checkAfter, () -> {
                prepareCheck(context);
            });
        } else {
            prepareCheck(context);
        }
    }

    @Override
    public void invokeTestMethod(GameTestHelper context, Method method) throws ReflectiveOperationException {
        invoked = false;
        EventBus.subscribe(this.listener);
    }
}
