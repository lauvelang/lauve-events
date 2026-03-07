package com.example.mod.entity;

import com.example.mod.EventTest;
import lol.sylvie.lauve.events.entity.BodyArrowCountChangeEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.Mannequin;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import java.lang.reflect.Method;

public class BodyArrowCountChangeTest extends EventTest<BodyArrowCountChangeEvent> {
    private Mannequin mannequin;

    public BodyArrowCountChangeTest() {
        super(5);
    }

    @Override
    public void handleEvent(BodyArrowCountChangeEvent event) {
        event.setNewAmount(42);
    }

    @Override
    protected void checkSuccessful(GameTestHelper context) {
        super.checkSuccessful(context);

        context.assertTrue(mannequin.getArrowCount() == 42, "Arrow body count change failed");
    }

    @Override
    public void invokeTestMethod(GameTestHelper context, Method method) throws ReflectiveOperationException {
        super.invokeTestMethod(context, method);

        context.setBlock(BlockPos.ZERO, Blocks.OBSIDIAN);
        this.mannequin = context.spawn(EntityType.MANNEQUIN, new Vec3(0.5, 1, 0.5));
        context.spawn(EntityType.ARROW, new Vec3(0.5, 3.25, 0.5));

        method.invoke(this, context);
    }
}
