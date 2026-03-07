package com.example.mod.entity;

import com.example.mod.EventTest;
import lol.sylvie.lauve.events.api.EventBus;
import lol.sylvie.lauve.events.api.Listener;
import lol.sylvie.lauve.events.entity.AreaEffectCloudApplyEvent;
import net.fabricmc.fabric.api.gametest.v1.CustomTestMethodInvoker;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.phys.Vec3;

import java.lang.reflect.Method;

public class AreaEffectCloudApplyTest extends EventTest<AreaEffectCloudApplyEvent> {
    public AreaEffectCloudApplyTest() {
        super(6);
    }

    @Override
    public void invokeTestMethod(GameTestHelper context, Method method) throws ReflectiveOperationException {
        super.invokeTestMethod(context, method);

        AreaEffectCloud cloud = context.spawn(EntityType.AREA_EFFECT_CLOUD, new Vec3(0, 0.5, 0));
        cloud.setWaitTime(0);
        cloud.setDuration(7);
        cloud.setPotionContents(PotionContents.EMPTY.withPotion(Potions.POISON));

        method.invoke(this, context);
    }
}