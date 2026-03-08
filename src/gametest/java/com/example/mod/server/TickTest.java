package com.example.mod.server;

import com.example.mod.EventTest;
import lol.sylvie.lauve.events.server.StartTickEvent;
import net.minecraft.gametest.framework.GameTestHelper;

import java.lang.reflect.Method;

public class TickTest extends EventTest<StartTickEvent> {
    public TickTest() {
        super(1);
    }

    @Override
    public void invokeTestMethod(GameTestHelper context, Method method) throws ReflectiveOperationException {
        super.invokeTestMethod(context, method);

        method.invoke(this, context);
    }
}
